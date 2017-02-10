package pl.pniedziela.scheduler.email;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import pl.pniedziela.email.Email;

@Entity
public class ScheduledEmailTask {

	@Id
	@SequenceGenerator(name = "scheduledEmail_id_seq", sequenceName = "scheduledEmail_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "scheduledEmail_id_seq")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date endDate;
	private int emailsCount;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Email> emailsList;

	public ScheduledEmailTask() {
		this.startDate = new Date();
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getEmailsCount() {
		return emailsCount;
	}

	public void setEmailsCount(int emailsCount) {
		this.emailsCount = emailsCount;
	}

	public List<Email> getEmailsList() {
		return emailsList;
	}

	public void setEmailsList(List<Email> emailsList) {
		this.emailsList = emailsList;
	}

	public long getId() {
		return id;
	}

}
