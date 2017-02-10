package pl.pniedziela.admin.sessions;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminSessionsController {

	@RequestMapping("/sessions")
	public String getSessionsPage(Model model) {

		return "admin.sessions";
	}
}
