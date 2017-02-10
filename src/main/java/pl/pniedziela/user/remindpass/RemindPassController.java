package pl.pniedziela.user.remindpass;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.pniedziela.user.User;

@Controller
public class RemindPassController {

	@Autowired
	RemindPassService rpService;

	@RequestMapping(value = "/remindPassword", method = RequestMethod.GET)
	public String getRemindPassPage(Model model) {

		RemindPassDto remindPassDto = new RemindPassDto();
		model.addAttribute("remindPassDto", remindPassDto);

		return "users.remindPass";
	}

	@RequestMapping(value = "/remindPassword", method = RequestMethod.POST)
	public String postRemindPassForm(@Valid RemindPassDto remindPassDto, BindingResult result, Model model) {

		User user;
		if (result.hasErrors()) {

			model.addAttribute("remindPassDto", remindPassDto);
			return "users.remindPass";
		} else if ((user = rpService.findUserByEmail(remindPassDto.getEmail())) == null) {
			result.rejectValue("email", "user.RemindPass.WrongEmail", null, null);
			return "users.remindPass";
		} else {

			rpService.generateLinkAndSendEmail(user);

			return "redirect:/remindPasswordEmailSent";
		}
	}

	@RequestMapping("/remindPasswordEmailSent")
	public String afterMailWithLinkSent(Model model) {
		model.addAttribute("successAlert", "user.RemindPass.EmailSent");

		return "home.mainPage";
	}

	@RequestMapping("/remindPassword/{rpCode}")
	public String checkCodeAndSetPass(@PathVariable String rpCode, Model model) {

		if (rpService.findUserByForgetPassLink(rpCode) != null) {
			NewPasswordDto newPasswordDto = new NewPasswordDto();
			newPasswordDto.setRemindPassCode(rpCode);
			model.addAttribute(newPasswordDto);
			return "users.setNewPass";
		} else {

			model.addAttribute("errorAlert", "user.RemindPass.WrongCode");
			return "home.mainPage";
		}
	}

	@RequestMapping(value = "/remindPassword/setNewPass", method = RequestMethod.POST)
	public String setNewPassword(@Valid NewPasswordDto newPasswordDto, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "users.setNewPass";
		} else {

			rpService.setNewPasswordForUser(newPasswordDto);
			return "redirect:/remindPasswordNewPassIsSet";
		}
	}

	@RequestMapping("/remindPasswordNewPassIsSet")
	public String afterSetNewPass(Model model) {

		model.addAttribute("successAlert", "user.RemindPass.NewPassIsSet");
		return "users.login";
	}
}
