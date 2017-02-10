package pl.pniedziela.email;

import javax.mail.Message.RecipientType;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomEmailSender {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	EmailDao emailDao;

	public void sendMail(Email email) {

		MimeMessage message = mailSender.createMimeMessage();
		email = emailDao.save(email);
		try {
			message.setFrom(new InternetAddress(email.getFromAddress()));
			message.setRecipient(RecipientType.TO, new InternetAddress("testowyjakis19@gmail.com"));
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
			mailSender.send(message);
			email.setSendDate(new Date());
		} catch (AddressException e) {
			email.addError(e.toString());
			e.printStackTrace();
		} catch (MessagingException e) {
			email.addError(e.toString());
			e.printStackTrace();
		}

		emailDao.save(email);
	}
}