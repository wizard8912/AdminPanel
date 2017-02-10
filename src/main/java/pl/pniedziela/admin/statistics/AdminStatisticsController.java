package pl.pniedziela.admin.statistics;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminStatisticsController {

	@RequestMapping("/statistics")
	public String getStatisticsPage(Model model) {

		return "admin.statistics";
	}
}
