package pl.pniedziela.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pl.pniedziela.user.UserService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetails matchingUser = userService.findByNickname(username);
		if (matchingUser == null) {
			throw new UsernameNotFoundException("Wrong username or password");
		}

		return matchingUser;
	}
}