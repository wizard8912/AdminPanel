package pl.pniedziela.user.account;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pniedziela.user.User;
import pl.pniedziela.user.UserDto;
import pl.pniedziela.user.UserService;

@Controller
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PreAuthorize("#userId == principal.id || hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/changePassword", method = RequestMethod.GET)
	public String getChangePasswordPage(Model model, @PathVariable(value = "userId") long userId) {

		NewPasswordDto newPasswordDto = new NewPasswordDto();
		newPasswordDto.setUserId(userId);
		model.addAttribute("newPasswordDto", newPasswordDto);

		return "account.changePassword";
	}

	@PreAuthorize("#newPasswordDto.getUserId() == principal.id || hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/setNewPass", method = RequestMethod.POST)
	public String setNewPassword(@Valid NewPasswordDto newPasswordDto, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "account.changePassword";
		} else {

			accountService.changePassword(newPasswordDto);
			return "redirect:/user/passwordChanged";
		}
	}

	@RequestMapping("/passwordChanged")
	public String afterSetNewPass(Model model) {

		model.addAttribute("successAlert", "account.ChangePassword.NewPassIsSet");
		return "home.mainPage";
	}

	@PreAuthorize("#userId == principal.id || hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/changeAccount", method = RequestMethod.GET)
	public String getChangeAccountPage(Model model, @PathVariable(value = "userId") long userId) {

		UserDto userDto = accountService.getUserDtoByUserId(userId);
		model.addAttribute("userDto", userDto);

		return "account.changeAccount";
	}

	@PreAuthorize("#userDto.getId() == principal.id || hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/changeAccount", method = RequestMethod.POST)
	public String changeUser(@Valid UserDto userDto, BindingResult result) {
		boolean nicknameExists = false;
		boolean emailExists = false;

		int totalErrors = result.getAllErrors().size();
		int passwordErrors = result.getFieldErrorCount("password") + result.getFieldErrorCount("passwordVerify");

		if (totalErrors != passwordErrors
				|| (nicknameExists = accountService.checkExistsByNicknameAndIdNot(userDto.getNickname(),
						userDto.getId()))
				|| (emailExists = accountService.checkExistsByEmailAndIdNot(userDto.getEmail(), userDto.getId()))) {

			if (nicknameExists)
				result.rejectValue("nickname", "user.SignUp.NicknameExists", null, null);

			if (emailExists)
				result.rejectValue("email", "user.SignUp.EmailExists", null, null);

			return "account.changeAccount";
		} else {

			accountService.changeUserFromDtoWithoutPassword(userDto);
			return "redirect:/user/accountChanged";
		}
	}

	@RequestMapping("/accountChanged")
	public String afterAccountChanged(Model model) {

		model.addAttribute("successAlert", "account.ChangeAccount.AccountChanged");
		return "home.mainPage";
	}

	@PreAuthorize("#userId != principal.id && @accountService.findUserById(#userId).isAdmin() ? hasRole('ROLE_SUPERADMIN') : hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/changeRole", method = RequestMethod.GET)
	public String getChangeRolePage(@PathVariable(value = "userId") long userId) {

		return "account.changeRole";
	}

	@PreAuthorize("#userId != principal.id && #role > 2 ? hasRole('ROLE_SUPERADMIN') : hasRole('ROLE_ADMIN') && @accountService.findUserById(#userId).isAdmin() ? hasRole('ROLE_SUPERADMIN') : hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/changeRole", method = RequestMethod.POST)
	public String setUsersRoles(@PathVariable(value = "userId") long userId, @RequestParam int role) {

		accountService.changeUsersRole(userId, role);
		return "home.mainPage";
	}

	@RequestMapping("/account")
	public String getAccountPage(HttpServletRequest request, Model model, Authentication auth) {

		User user = (User) auth.getPrincipal();
		model.addAttribute("userDetails", user);
		model.addAttribute("accountDetails", accountService.getAccountDetails(user));
		return "user.account";
	}

	@RequestMapping("/account/{userId}")
	@PreAuthorize("#userId == principal.id || hasRole('ROLE_ADMIN')")
	public String getAccountPage(@PathVariable(value = "userId") long userId, HttpServletRequest request, Model model,
			Authentication auth) {

		User user = accountService.findUserById(userId);
		model.addAttribute("userDetails", user);
		model.addAttribute("accountDetails", accountService.getAccountDetails(user));
		if (request.isUserInRole("ADMIN")) {
			boolean accountNotActivated = user.getActivateLink() != null;
			boolean accountWithForgotPass = user.getForgetPassLink() != null;

			if (accountNotActivated && accountWithForgotPass) {
				model.addAttribute("infoAlert", "admin.AccountInfo.NotActivatedAndForgetPass");
			} else if (accountNotActivated) {
				model.addAttribute("infoAlert", "admin.AccountInfo.NotActivated");
			} else if (accountWithForgotPass) {
				model.addAttribute("infoAlert", "admin.AccountInfo.ForgetPass");
			}

			model.addAttribute("accountNotActivated", accountNotActivated);
			model.addAttribute("accountWithForgotPass", accountWithForgotPass);
		}
		return "user.account";
	}
}
