package pl.pniedziela.sessions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

	@Autowired
	SessionDetailsDao sessionDetailsDao;

	public List<BrowserStat> getBrowserUsageStats() {
		return sessionDetailsDao.getBrowserUsageStat();
	}

	public List<OsStat> getOsUsageStats() {
		return sessionDetailsDao.getOsUsageStat();
	}

	public List<VisitStat> getVisitStats() {
		return sessionDetailsDao.getVisitsStat();
	}
}
