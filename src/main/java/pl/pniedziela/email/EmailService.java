package pl.pniedziela.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.scheduler.email.ScheduledEmailTask;

@Service
@Transactional
@EnableAsync
public class EmailService {

	@Autowired
	CustomEmailSender mailSender;
	@Autowired
	EmailDao emailDao;

	@Async
	public void sendEmail(String to, String subject, String message) {
		Email email = new Email();
		email.setFromAddress("testowyjakis19@gmail.com");
		email.setToAddress(to);
		email.setSubject(subject);
		email.setMessage(message);

		sendEmail(email);
	}

	@Async
	public void sendEmail(Email email) {
		mailSender.sendMail(email);
	}

	public void sendNotAsyncEmail(Email email) {
		mailSender.sendMail(email);
	}

	public Email saveEmail(Email email) {
		return emailDao.save(email);
	}

	public DataTablesOutput<Email> findAll(DataTablesInput input) {
		return emailDao.findAll(input);
	}

	public List<Email> findUnsentEmails() {
		return emailDao.findBySendDateIsNull();
	}

}
