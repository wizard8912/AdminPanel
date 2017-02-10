package pl.pniedziela.user.account;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.sessions.SessionDetails;
import pl.pniedziela.sessions.SessionsDetailsService;
import pl.pniedziela.user.AccountDetails;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserDto;
import pl.pniedziela.user.UserService;

@Service
public class AccountService {

	@Autowired
	UserService userService;
	@Autowired
	SessionsDetailsService sessionDetailsService;
	@Autowired
	LogService logService;
	@Autowired
	StandardPasswordEncoder passwordEncoder;

	@PreAuthorize("#newPasswordDto.getUserId() == principal.id || hasRole('ROLE_ADMIN')")
	public void changePassword(NewPasswordDto newPasswordDto) {

		User user = userService.findById(newPasswordDto.getUserId());
		user.setPassword(passwordEncoder.encode(newPasswordDto.getPassword()));
		user.setForgetPassDate(null);
		user.setForgetPassLink(null);

		userService.saveUser(user);
	}

	public UserDto getUserDtoByUserId(long userId) {

		return userService.getUserDtoByUserId(userId);
	}

	public void changeUserFromDtoWithoutPassword(UserDto userDto) {

		userService.changeUserFromDtoWithoutPassword(userDto);
	}

	public boolean checkExistsByNicknameAndIdNot(String nickname, long id) {

		return userService.checkExistsByNicknameAndIdNot(nickname, id);
	}

	public boolean checkExistsByEmailAndIdNot(String email, long id) {

		return userService.checkExistsByEmailAndIdNot(email, id);
	}

	public User findUserById(long userId) {
		return userService.findById(userId);
	}

	public void changeUsersRole(long userId, int role) {

		userService.changeUserRoleById(userId, role);
	}

	public AccountDetails getAccountDetails(User user) {

		AccountDetails accountDetails = new AccountDetails();
		List<Log> successLogins = logService.findSuccessByUser(user);
		accountDetails.setLoginCount(successLogins.size());
		if (successLogins.size() > 0) {
			Date lastSuccessLogin = successLogins.get(0).getDate();
			for (Log log : successLogins) {
				if (log.getDate().getTime() > lastSuccessLogin.getTime())
					lastSuccessLogin = log.getDate();
			}
			accountDetails.setLastLogin(lastSuccessLogin);
		}

		List<Log> failedLogins = logService.findFailedByUser(user);
		accountDetails.setFailedLoginCount(failedLogins.size());
		if (failedLogins.size() > 0) {
			Date lastFailedLogin = failedLogins.get(0).getDate();
			for (Log log : failedLogins) {
				if (log.getDate().getTime() > lastFailedLogin.getTime())
					lastFailedLogin = log.getDate();
			}
			accountDetails.setLastFailedLogin(lastFailedLogin);
		}
		return accountDetails;
	}

}
