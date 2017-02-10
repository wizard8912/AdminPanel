package pl.pniedziela.security;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import pl.pniedziela.logs.LogService;
import pl.pniedziela.sessions.SessionDetails;
import pl.pniedziela.sessions.SessionsDetailsService;
import pl.pniedziela.user.UserService;

public class AutoLoginProvider extends PersistentTokenBasedRememberMeServices {

	private PersistentTokenRepository tokenRepository;
	@Autowired
	SessionsDetailsService sessionsDetailsService;
	@Autowired
	UserService userService;

	public AutoLoginProvider(String key, UserDetailsService userDetailsService,
			PersistentTokenRepository tokenRepository) {
		super(key, userDetailsService, tokenRepository);
		this.tokenRepository = tokenRepository;
	}

	protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
			HttpServletResponse response) {

		if (cookieTokens.length != 2) {
			throw new InvalidCookieException("Cookie token did not contain " + 2 + " tokens, but contained '"
					+ Arrays.asList(cookieTokens) + "'");
		}

		final String presentedSeries = cookieTokens[0];
		final String presentedToken = cookieTokens[1];

		PersistentRememberMeToken token = tokenRepository.getTokenForSeries(presentedSeries);

		if (token == null) {
			// No series match, so we can't authenticate using this cookie
			throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
		}

		// We have a match for this user/series combination
		if (!presentedToken.equals(token.getTokenValue())) {
			// Token doesn't match series value. Delete all logins for this user
			// and throw
			// an exception to warn them.
			tokenRepository.removeUserTokens(token.getUsername());

			throw new CookieTheftException(messages.getMessage("PersistentTokenBasedRememberMeServices.cookieStolen",
					"Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."));
		}

		if (token.getDate().getTime() + getTokenValiditySeconds() * 1000L < System.currentTimeMillis()) {
			throw new RememberMeAuthenticationException("Remember-me login has expired");
		}

		// Token also matches, so login is valid. Update the token value,
		// keeping the
		// *same* series number.
		if (logger.isDebugEnabled()) {
			logger.debug("Refreshing persistent login token for user '" + token.getUsername() + "', series '"
					+ token.getSeries() + "'");
		}

		PersistentRememberMeToken newToken = new PersistentRememberMeToken(token.getUsername(), token.getSeries(),
				generateTokenData(), new Date());

		try {

			// tokenRepository.updateToken(newToken.getSeries(),
			// newToken.getTokenValue(), newToken.getDate());
			addCookie(token, request, response);

			System.out.println("TRY");
		} catch (Exception e) {
			logger.error("Failed to update token: ", e);
			throw new RememberMeAuthenticationException("Autologin failed due to data access problem");
		}

		SessionDetails session = sessionsDetailsService.findById(request.getSession().getId());
		UserDetails user = getUserDetailsService().loadUserByUsername(token.getUsername());
		session.setUser(userService.findByNickname(user.getUsername()));
		sessionsDetailsService.save(session);
		return user;

	}

	private void addCookie(PersistentRememberMeToken token, HttpServletRequest request, HttpServletResponse response) {
		setCookie(new String[] { token.getSeries(), token.getTokenValue() }, getTokenValiditySeconds(), request,
				response);
	}
}
