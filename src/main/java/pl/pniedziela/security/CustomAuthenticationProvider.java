package pl.pniedziela.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@Transactional
public class CustomAuthenticationProvider implements AuthenticationProvider, AuthenticationManager {

	@Autowired
	StandardPasswordEncoder passwordEncoder;
	@Autowired
	UserService userService;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("AUTHENTICATION");
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userService.findByNickname(name);
		System.out.println("DEBUG: ");
		System.out.println(password);
		if (user == null) {
			System.out.println("==========================================");
			System.out.println("UserNotFoundException");
			System.out.println("==========================================");
			throw new UsernameNotFoundException(name);
		} else if (passwordEncoder.matches(password, user.getPassword()) || user.getGooglePassword() != null
				? passwordEncoder.matches(user.getGooglePassword(), user.getGoogleHashedPassword()) : false) {

			if (!user.isAccountNonLocked()) {
				System.out.println("==========================================");
				System.out.println("LockedException");
				System.out.println("==========================================");
				throw new LockedException(name);
			} else if (!user.isEnabled()) {
				System.out.println("==========================================");
				System.out.println("DisabledException");
				System.out.println("==========================================");
				throw new DisabledException(name);
			}

			Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
					user.getAuthorities());
			return auth;

		} else {
			System.out.println("==========================================");
			System.out.println("BadCredentialsException");
			System.out.println("==========================================");
			throw new BadCredentialsException(name);
		}
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}