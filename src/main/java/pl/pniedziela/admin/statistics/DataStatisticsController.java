package pl.pniedziela.admin.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.pniedziela.sessions.BrowserStat;
import pl.pniedziela.sessions.OsStat;
import pl.pniedziela.sessions.StatsService;
import pl.pniedziela.sessions.VisitStat;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataStatisticsController {

	@Autowired
	StatsService statsService;

	@RequestMapping(value = "/browsersusage", method = RequestMethod.GET)
	public List<BrowserStat> getBrowsersUsage() {

		List<BrowserStat> browserStats = statsService.getBrowserUsageStats();
		return browserStats;
	}

	@RequestMapping(value = "/osusage", method = RequestMethod.GET)
	public List<OsStat> getOsUsage() {

		List<OsStat> osStats = statsService.getOsUsageStats();
		return osStats;
	}

	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public List<VisitStat> getVisits() {

		List<VisitStat> visitStats = statsService.getVisitStats();
		return visitStats;
	}
}
