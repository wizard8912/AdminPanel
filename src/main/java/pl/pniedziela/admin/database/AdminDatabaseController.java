package pl.pniedziela.admin.database;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminDatabaseController {

	@RequestMapping("/database")
	public String getDatabasePage(Model model) {

		return "admin.database";
	}
}
