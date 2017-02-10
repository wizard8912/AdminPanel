package pl.pniedziela.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import pl.pniedziela.application.config.ApplicationConfig;
import pl.pniedziela.application.config.ApplicationConfigService;
import pl.pniedziela.bans.BanService;
import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.logs.LogTypes;
import pl.pniedziela.sessions.SessionDetails;
import pl.pniedziela.sessions.SessionsDetailsService;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@EnableAsync
public class AuthenticationHandler
		implements AuthenticationFailureHandler, AuthenticationSuccessHandler, LogoutSuccessHandler {

	@Autowired
	LogService logService;
	@Autowired
	BanService banService;
	@Autowired
	ApplicationConfigService applicationConfigService;
	@Autowired
	SessionsDetailsService sessionDetailsService;
	@Autowired
	UserService userService;

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		boolean banned = false;

		if (banService.getActiveBanForUsernameOrIpAddress(request.getRemoteUser(), request.getRemoteAddr()) != null) {
			response.sendRedirect(request.getContextPath() + "/banned");
			SecurityContextHolder.clearContext();
			request.getSession().invalidate();
			banned = true;
		}

		Log log = new Log();
		log.setUsername(exception.getMessage());
		log.setDate(new Date());
		log.setIpAddress(request.getRemoteAddr());
		ApplicationConfig appConfig = applicationConfigService.getApplicationConfig();

		DateTime from = new DateTime();
		from = from.minusSeconds(appConfig.getBanCheckSeconds());

		if (!banned) {
			if (appConfig.isBanForIp()) {
				int failedLoginAttempsIpAddr = logService.getLastFailedCountForIpAddress(request.getRemoteAddr(),
						from.toDate());

				if (failedLoginAttempsIpAddr >= appConfig.getBanLoginAttempsIp()) {
					DateTime dt = new DateTime();
					dt = dt.plusSeconds(appConfig.getBanForSeconds());
					banService.addBanForIpAddress(request.getRemoteAddr(), new Date(), dt.toDate(), "b³êdne logowania");
				}
			}

			if (appConfig.isBanForUsr()) {
				int failedLoginAttempsUsr = logService.getLastFailedCountForUsername(exception.getMessage(),
						from.toDate());

				if (failedLoginAttempsUsr >= appConfig.getBanLoginAttempsUsr()) {
					DateTime dt = new DateTime();
					dt = dt.plusSeconds(appConfig.getBanForSeconds());
					banService.addBanForUser(exception.getMessage(), new Date(), dt.toDate(), "b³êdne logowania");
				}
			}
		}

		if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class))

		{

			log.setLogType(LogTypes.FAILTURE_USERNAME_NOT_FOUND);
			if (!banned)
				response.sendRedirect(request.getContextPath() + "/login/usernameNotFound");
			logService.saveLog(log);

		} else if (exception.getClass().isAssignableFrom(BadCredentialsException.class))

		{

			log.setLogType(LogTypes.FAILTURE_BAD_PASSWORD);
			if (!banned)
				response.sendRedirect(request.getContextPath() + "/login/badCredentials");
			logService.saveLog(log);

		} else if (exception.getClass().isAssignableFrom(LockedException.class))

		{
			log.setLogType(LogTypes.FAILTURE_BANNED_USER);
			request.setAttribute("username", exception.getMessage());
			if (!banned) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login/banned");
				dispatcher.forward(request, response);
			}
			logService.saveLog(log);
		} else if (exception.getClass().isAssignableFrom(DisabledException.class))

		{
			log.setLogType(LogTypes.FAILTURE_ACCOUNT_DISABLED);
			if (!banned)
				response.sendRedirect(request.getContextPath() + "/login/accountDisabled");
			logService.saveLog(log);
		}

	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		if (banService.getActiveBanForUsernameOrIpAddress(request.getRemoteUser(), request.getRemoteAddr()) != null) {
			response.sendRedirect(request.getContextPath() + "/banned");
			SecurityContextHolder.clearContext();
			request.getSession().invalidate();
		} else {
			SessionDetails sessionDetails = sessionDetailsService.findById(request.getSession().getId());
			sessionDetails.setUser((User) authentication.getPrincipal());
			sessionDetailsService.save(sessionDetails);

			if (RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
				userService.logRememberMeLogin(authentication.getName(), request.getRemoteAddr());
			} else {
				userService.logSuccessLogin(authentication.getName(), request.getRemoteAddr());
			}
			response.sendRedirect(request.getContextPath() + "/logged");
		}
	}

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("LOGOUT");
	}
}