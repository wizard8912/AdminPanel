package pl.pniedziela.user.activation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@Service
public class ActivationService {

	@Autowired
	UserService userService;

	public boolean activateAccountByActivateLink(String activateLink) {

		User user = userService.findByActivateLink(activateLink);
		if (user == null) {
			return false;
		}
		user.setActivateDate(new Date());
		user.setActivateLink(null);

		userService.saveUser(user);

		return true;
	}
}
