package pl.pniedziela.admin.users;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pniedziela.user.UserDto;
import pl.pniedziela.user.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminUsersController {

	@Autowired
	UserService userService;

	@RequestMapping("/users")
	public String getUsersPage(Model model) {

		UserDto userDto = new UserDto();
		model.addAttribute(userDto);
		return "admin.users";
	}

	@RequestMapping("/deletedUsers")
	public String getDeletedUsersPage(Model model) {

		return "admin.deletedUsers";
	}

	@RequestMapping("/changeuser")
	public String changeUser(@Valid UserDto userDto, BindingResult result) {
		boolean nicknameExists = false;
		boolean emailExists = false;

		int totalErrors = result.getAllErrors().size();
		int passwordErrors = result.getFieldErrorCount("password") + result.getFieldErrorCount("passwordVerify");

		if (totalErrors != passwordErrors
				|| (nicknameExists = userService.checkExistsByNicknameAndIdNot(userDto.getNickname(), userDto.getId()))
				|| (emailExists = userService.checkExistsByEmailAndIdNot(userDto.getEmail(), userDto.getId()))) {

			if (nicknameExists)
				result.rejectValue("nickname", "user.SignUp.NicknameExists", null, null);

			if (emailExists)
				result.rejectValue("email", "user.SignUp.EmailExists", null, null);

			return "admin.users";
		} else {

			userService.changeUserFromDtoWithoutPassword(userDto);
			return "redirect:/admin/changeuser/complete";
		}
	}

	@RequestMapping("/changeuser/complete")
	public String changedUser(Model model) {

		model.addAttribute("successAlert", "admin.Users.UserChanged");
		return getUsersPage(model);
	}

	@RequestMapping("/changepassword")
	public String changePassword(@Valid UserDto userDto, BindingResult result) {

		if (result.getErrorCount() != 2) {
			return "admin.users";
		} else {
			userService.changePasswordFromDto(userDto);
			return "redirect:/admin/changepassword/complete";
		}
	}

	@RequestMapping("/changepassword/complete")
	public String changedPassword(Model model) {

		model.addAttribute("successAlert", "admin.Users.PasswordChanged");
		return getUsersPage(model);
	}

	@RequestMapping("/changeroles")
	@PreAuthorize("#role == 2 || @userService.findById(#id).isSuperAdmin() ? hasRole('ROLE_SUPERADMIN') : hasRole('ROLE_ADMIN')")
	public String changeRoles(@RequestParam int id, @RequestParam int role) {

		userService.changeUserRoleById(id, role);
		return "redirect:/admin/changeroles/complete";
	}

	@RequestMapping("/changeroles/complete")
	public String changedRoles(Model model) {

		model.addAttribute("successAlert", "admin.Users.RolesChanged");
		return getUsersPage(model);
	}
}
