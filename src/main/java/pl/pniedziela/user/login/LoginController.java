package pl.pniedziela.user.login;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pniedziela.bans.Ban;
import pl.pniedziela.bans.BanService;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.security.CustomAuthenticationProvider;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;
import pl.pniedziela.user.social.UserGoogle;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	@Autowired
	BanService banService;
	@Autowired
	JdbcTokenRepositoryImpl tokenRepository;
	@Autowired
	@Qualifier("authenticationManager")
	protected AuthenticationManager authenticationManager;

	@RequestMapping("/login")
	public String getLoginPage() {

		return "users.login";
	}

	@RequestMapping("/logged")
	public String getAfterLoginPage(Model model, HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		model.addAttribute("successAlert", "Users.Login.SuccessLogin");

		return "home.mainPage";
	}

	@RequestMapping("/logout")
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) {

		tokenRepository.removeUserTokens(request.getRemoteUser());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = request.getRemoteUser();
		String ipaddress = request.getRemoteAddr();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		loginService.logLogout(username, ipaddress);
		return "redirect:/afterLogout";
	}

	@RequestMapping("/afterLogout")
	public String getAfterLogoutPage(Model model) {

		model.addAttribute("successAlert", "user.logout.Success");
		return "users.login";
	}

	@RequestMapping("/login/usernameNotFound")
	public String getLoginPageWithErrorUsrNotFound(Model model) {

		model.addAttribute("errorAlert", "Users.Login.UsernameNotFound");
		return "users.login";
	}

	@RequestMapping("/login/badCredentials")
	public String getLoginPageWithErrorBadCredentials(Model model) {

		model.addAttribute("errorAlert", "Users.Login.BadCredentials");
		return "users.login";
	}

	@RequestMapping("/login/accountDisabled")
	public String getLoginPageWithErrorAccountDisabled(Model model) {

		model.addAttribute("errorAlert", "Users.Login.AccountDisabled");
		return "users.login";
	}

	@RequestMapping("/login/banned")
	public String getLoginPageWithBannedInfo(Model model, HttpServletRequest request) {

		User user = userService.findByNickname((String) request.getAttribute("username"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		model.addAttribute("errorAlert", "Users.Login.Banned");
		model.addAttribute("errorMessageAttribute", dt.format(user.getLatestActiveBan().getDateTo()));
		return "users.login";
	}

	@RequestMapping("/banned")
	public String getLoginPageWithBannedIpInfo(Model model, HttpServletRequest request) {
		loginService.logBanned(request.getRemoteUser(), request.getRemoteAddr());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Ban ban = banService.getActiveBanForUsernameOrIpAddress(request.getRemoteUser(), request.getRemoteAddr());
		model.addAttribute("errorAlert", "Users.Login.Banned");
		model.addAttribute("errorMessageAttribute", dt.format(ban.getDateTo()));
		return "users.login";
	}

	@RequestMapping(value = "/auth/google", method = RequestMethod.POST)
	public String authorizeByGoogleAccount(@ModelAttribute UserGoogle userGoogle, HttpServletRequest request,
			Model model) {

		User user = userService.findByGoogleID(userGoogle.getGoogleAuthID());
		if (user == null) {
			if (userService.checkExistsByEmail(userGoogle.getGoogleAuthEmail())) {
				user = userService.connectAccountWithGoogle(userGoogle);
			} else {
				user = userService.registerNewUserFromGoogle(userGoogle);
			}
		}

		if (doAutoLogin(user.getNickname(), user.getGooglePassword(), request)) {
			return "redirect:/logged";
		} else {
			return "redirect:/406";
		}
	}

	private boolean doAutoLogin(String username, String password, HttpServletRequest request) {

		try {
			// Must be called from request filtered by Spring Security,
			// otherwise SecurityContextHolder is not updated
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			System.out.println(1);
			token.setDetails(new WebAuthenticationDetails(request));
			System.out.println(2);
			Authentication authentication = this.authenticationManager.authenticate(token);
			System.out.println(3);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println(4);
			loginService.logGoogleSuccessLogin(username, request.getRemoteAddr());
			System.out.println(5);
			return true;
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			loginService.logGoogleFailedLogin(username, request.getRemoteAddr());
			return false;
		}

	}
}
