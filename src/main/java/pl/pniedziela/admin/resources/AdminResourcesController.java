package pl.pniedziela.admin.resources;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminResourcesController {

	@RequestMapping("/resources")
	public String getResourcesPage(Model model) {

		return "admin.resources";
	}
}
