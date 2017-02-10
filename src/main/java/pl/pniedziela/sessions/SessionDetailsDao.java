package pl.pniedziela.sessions;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.pniedziela.user.User;

public interface SessionDetailsDao extends DataTablesRepository<SessionDetails, Long> {

	public <S extends SessionDetails> S save(S entity);

	public SessionDetails findBySessionID(String sessionID);

	@Query("SELECT new pl.pniedziela.sessions.BrowserStat(s.userBrowser, count(s)) FROM SessionDetails s GROUP BY s.userBrowser")
	public List<BrowserStat> getBrowserUsageStat();

	@Query("SELECT new pl.pniedziela.sessions.OsStat(s.userOS, count(s)) FROM SessionDetails s GROUP BY s.userOS")
	public List<OsStat> getOsUsageStat();

	@Query("SELECT new pl.pniedziela.sessions.VisitStat(cast(s.createdDate as date), count(distinct s.ipAddress), count(s)) FROM SessionDetails s GROUP BY cast(s.createdDate as date)")
	public List<VisitStat> getVisitsStat();

	public List<SessionDetails> findAllByDestroyedDate(Date object);

	public List<SessionDetails> findByUser(User user);

	public List<SessionDetails> findByDestroyedDateAndUser(Date destroyedDate, User user);
}
