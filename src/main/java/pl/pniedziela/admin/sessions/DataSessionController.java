package pl.pniedziela.admin.sessions;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.email.Email;
import pl.pniedziela.email.EmailService;
import pl.pniedziela.sessions.SessionDetails;
import pl.pniedziela.sessions.SessionsDetailsService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataSessionController {
	@Autowired
	SessionsDetailsService sessionsDetailsService;

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public DataTablesOutput<SessionDetails> getSessionsData(@Valid DataTablesInput input) {
		DataTablesOutput<SessionDetails> listSessions;
		listSessions = sessionsDetailsService.findAll(input);
		return listSessions;
	}
}
