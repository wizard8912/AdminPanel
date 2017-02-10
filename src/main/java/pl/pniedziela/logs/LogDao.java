package pl.pniedziela.logs;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends DataTablesRepository<Log, Long> {

	public List<Log> findByUsernameAndDateGreaterThanAndLogTypeIn(String username, Date date,
			Collection<LogTypes> logType);

	public List<Log> findByIpAddressAndDateGreaterThanAndLogTypeIn(String ipAddress, Date from,
			Collection<LogTypes> logTypes);

	public Page<Log> findByUsernameAndLogTypeIn(String username, Collection<LogTypes> logTypes, Pageable pageable);

	public <S extends Log> S save(S entity);

	public List<Log> findByLogTypeInAndUsername(Collection<LogTypes> logTypes, String nickname);
}
