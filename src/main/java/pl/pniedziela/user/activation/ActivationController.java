package pl.pniedziela.user.activation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activate")
public class ActivationController {

	@Autowired
	ActivationService activationService;

	@RequestMapping("/{activateLink}")
	public String activateAccount(@PathVariable String activateLink, Model model) {

		if (activationService.activateAccountByActivateLink(activateLink)) {
			model.addAttribute("successAlert", "user.Activation.AccountActivated");
			return "home.mainPage";
		} else {
			model.addAttribute("errorAlert", "user.Activation.ActivatedError");
			return "error.404";
		}
	}
}
