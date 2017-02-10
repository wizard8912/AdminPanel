package pl.pniedziela.sessions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;

import pl.pniedziela.user.User;

@Service
public class SessionsDetailsService {

	@Autowired
	SessionDetailsDao sessionDetailsDao;
	@Autowired
	SessionRegistryImpl sessionRegistry;

	public SessionDetails save(SessionDetails sessionDetails) {
		return sessionDetailsDao.save(sessionDetails);
	}

	public SessionDetails findById(String sessionID) {
		return sessionDetailsDao.findBySessionID(sessionID);
	}

	public void destroyAllActiveSessions() {
		List<SessionDetails> activeSessions = sessionDetailsDao.findAllByDestroyedDate(null);

		for (SessionDetails sessionDetails : activeSessions) {
			sessionDetails.setDestroyedDate(new Date());
			sessionDetailsDao.save(sessionDetails);
		}
	}

	public DataTablesOutput<SessionDetails> findAll(DataTablesInput input) {

		return sessionDetailsDao.findAll(input);
	}

	public void destroyUserSessions(User user) {
		List<SessionDetails> findActiveByUser = findActiveByUser(user);
		System.out.println(findActiveByUser);
		for (SessionDetails sessionDetails : findActiveByUser) {
			SessionInformation sessionInformation = sessionRegistry
					.getSessionInformation(sessionDetails.getSessionID());
			System.out.println(sessionInformation);

		}
		System.out.println(sessionRegistry.getAllPrincipals().size());

	}

	public List<SessionDetails> findActiveByUser(User user) {
		return sessionDetailsDao.findByDestroyedDateAndUser(null, user);
	}
}
