package pl.pniedziela.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterPage(Model model) {

		UserDto userDto = new UserDto();
		model.addAttribute(userDto);

		return "users.register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegisterPage(@Valid UserDto userDTO, BindingResult result, Model model) {
		boolean nicknameExists = false;
		boolean emailExists = false;

		if (result.hasErrors() || (nicknameExists = userService.checkExistsByNickname(userDTO.getNickname()))
				|| (emailExists = userService.checkExistsByEmail(userDTO.getEmail()))) {

			if (nicknameExists)
				result.rejectValue("nickname", "user.SignUp.NicknameExists", null, null);

			if (emailExists)
				result.rejectValue("email", "user.SignUp.EmailExists", null, null);

			return "users.register";
		} else {

			userService.registerNewUserFromDto(userDTO);
			return "redirect:/registerComplete";
		}
	}

	@RequestMapping("/registerComplete")
	public String getAfterRegistered(Model model) {

		model.addAttribute("successAlert", "user.SignUp.SuccessfullRegistered");
		return "home.mainPage";
	}

}
