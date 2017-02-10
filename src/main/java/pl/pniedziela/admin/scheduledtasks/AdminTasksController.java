package pl.pniedziela.admin.scheduledtasks;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
public class AdminTasksController {

	@RequestMapping("/emailTasks")
	public String getEmailTasksPage() {

		return "admin.emailTasks";
	}
}
