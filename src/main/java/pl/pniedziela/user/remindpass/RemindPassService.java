package pl.pniedziela.user.remindpass;

import java.util.Date;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.pniedziela.email.EmailService;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@Service
@EnableAsync
public class RemindPassService {

	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	StandardPasswordEncoder passwordEncoder;

	public User findUserByEmail(String email) {
		return userService.findByEmail(email);
	}

	public void generateLinkAndSendEmail(User user) {
		if (user.getForgetPassLink() == null) {
			user.setForgetPassLink(UUID.randomUUID().toString());
		}
		user.setForgetPassDate(new Date());
		userService.saveUser(user);

		StringBuilder message = new StringBuilder();
		message.append("Link do przypomnienia has³a: ");
		message.append("http://localhost:8080/ServiceDesk/remindPassword/");
		message.append(user.getForgetPassLink());
		message.append(" Link bêdzie wa¿ny przez 24 godziny!");

		emailService.sendEmail(user.getEmail(), "Przypomnienie has³a", message.toString());
	}

	public User findUserByForgetPassLink(String rpCode) {
		User user = userService.findByForgetPassLink(rpCode);

		if (user == null)
			return null;

		Minutes minutes = Minutes.minutesBetween(new DateTime(user.getForgetPassDate()), new DateTime(new Date()));

		return minutes.getMinutes() > 1440 ? null : user;
	}

	public void setNewPasswordForUser(NewPasswordDto newPasswordDto) {

		User user = userService.findByForgetPassLink(newPasswordDto.getRemindPassCode());
		user.setPassword(passwordEncoder.encode(newPasswordDto.getPassword()));
		user.setForgetPassDate(null);
		user.setForgetPassLink(null);

		userService.saveUser(user);

	}

}
