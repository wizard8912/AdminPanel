package pl.pniedziela.admin.logs;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminLogsController {

	@RequestMapping("/logs")
	public String getLogsPage(Model model) {

		return "admin.logs";
	}
}
