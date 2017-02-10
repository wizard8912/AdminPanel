package pl.pniedziela.admin.emails;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminEmailsController {

	@RequestMapping("/emails")
	public String getEmailsPage(Model model) {

		return "admin.emails";
	}
}
