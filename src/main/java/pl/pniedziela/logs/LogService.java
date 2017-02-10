package pl.pniedziela.logs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.sessions.SessionDetails;
import pl.pniedziela.user.User;

@Service
@Transactional
public class LogService {

	@Autowired
	LogDao logDao;

	@Async
	public Log saveLog(Log log) {

		return logDao.save(log);
	}

	public int getLastFailedCountForUsername(String username, Date from) {

		Collection<LogTypes> logTypes = new ArrayList<LogTypes>();
		logTypes.add(LogTypes.FAILTURE_BAD_PASSWORD);
		logTypes.add(LogTypes.FAILTURE_BANNED_USER);
		logTypes.add(LogTypes.FAILTURE_ACCOUNT_DISABLED);
		logTypes.add(LogTypes.FAILTURE_USERNAME_NOT_FOUND);
		logTypes.add(LogTypes.GOOGLE_FAILED_LOGIN);

		return logDao.findByUsernameAndDateGreaterThanAndLogTypeIn(username, from, logTypes).size();
	}

	public int getLastFailedCountForIpAddress(String ipAddress, Date from) {

		Collection<LogTypes> logTypes = new ArrayList<LogTypes>();
		logTypes.add(LogTypes.FAILTURE_BAD_PASSWORD);
		logTypes.add(LogTypes.FAILTURE_BANNED_USER);
		logTypes.add(LogTypes.FAILTURE_ACCOUNT_DISABLED);
		logTypes.add(LogTypes.FAILTURE_USERNAME_NOT_FOUND);
		logTypes.add(LogTypes.GOOGLE_FAILED_LOGIN);

		return logDao.findByIpAddressAndDateGreaterThanAndLogTypeIn(ipAddress, from, logTypes).size();
	}

	public DataTablesOutput<Log> findAll(DataTablesInput input) {
		return logDao.findAll(input);
	}

	public Log getLastSuccessLoginByUsername(String username) {
		final PageRequest page1 = new PageRequest(0, 20, Direction.DESC, "date");
		Collection<LogTypes> logTypes = new ArrayList<LogTypes>();
		logTypes.add(LogTypes.SUCCESS_LOGIN);
		logTypes.add(LogTypes.GOOGLE_SUCCESS_LOGIN);

		return logDao.findByUsernameAndLogTypeIn(username, logTypes, page1).getContent().get(0);
	}

	public List<Log> findFailedByUser(User user) {
		Collection<LogTypes> logTypes = new ArrayList<LogTypes>();
		logTypes.add(LogTypes.FAILTURE_ACCOUNT_DISABLED);
		logTypes.add(LogTypes.FAILTURE_BAD_PASSWORD);
		logTypes.add(LogTypes.FAILTURE_BANNED_USER);
		logTypes.add(LogTypes.GOOGLE_FAILED_LOGIN);

		return logDao.findByLogTypeInAndUsername(logTypes, user.getNickname());
	}

	public List<Log> findSuccessByUser(User user) {
		Collection<LogTypes> logTypes = new ArrayList<LogTypes>();
		logTypes.add(LogTypes.SUCCESS_LOGIN);
		logTypes.add(LogTypes.GOOGLE_SUCCESS_LOGIN);

		return logDao.findByLogTypeInAndUsername(logTypes, user.getNickname());
	}
}
