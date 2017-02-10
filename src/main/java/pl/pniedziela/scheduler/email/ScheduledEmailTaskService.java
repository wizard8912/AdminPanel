package pl.pniedziela.scheduler.email;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.email.Email;
import pl.pniedziela.email.EmailService;

@Service
@EnableScheduling()
@EnableAsync
@Transactional
public class ScheduledEmailTaskService {

	@Autowired
	EmailService emailService;
	@Autowired
	ScheduledEmailTaskDao scheduledEmailTaskDao;

	@Scheduled(cron = "0 40 * * * *")
	@Async
	public void sendUnsentMails() {
		ScheduledEmailTask scheduledTask = new ScheduledEmailTask();
		List<Email> unsentEmails;
		unsentEmails = emailService.findUnsentEmails();
		scheduledTask.setEmailsCount(unsentEmails.size());
		scheduledTask.setEmailsList(unsentEmails);
		saveTask(scheduledTask);
		for (Email email : unsentEmails) {
			emailService.sendNotAsyncEmail(email);
		}
		scheduledTask.setEndDate(new Date());
		saveTask(scheduledTask);
	}

	public ScheduledEmailTask saveTask(ScheduledEmailTask task) {

		return scheduledEmailTaskDao.save(task);
	}

	public DataTablesOutput<ScheduledEmailTask> findAll(DataTablesInput input) {

		return scheduledEmailTaskDao.findAll(input);
	}
}
