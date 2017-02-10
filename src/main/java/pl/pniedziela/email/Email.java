package pl.pniedziela.email;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = "findAllEmails", query = "SELECT e FROM Email e order by e.id desc")
public class Email {

	@Id
	@SequenceGenerator(name = "emails_id_seq", sequenceName = "emails_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "emails_id_seq")
	private long id;
	private String fromAddress;
	private String ToAddress;
	private String subject;
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date sendDate;
	@ElementCollection
	@JsonIgnore
	private Map<Date, String> errors;

	public Email() {
		this.createdDate = new Date();
		this.errors = new HashMap<Date, String>();
	}

	public void addError(String error) {
		this.errors.put(new Date(), error);
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return ToAddress;
	}

	public void setToAddress(String toAddress) {
		ToAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Map<Date, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<Date, String> errors) {
		this.errors = errors;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", fromAddress=" + fromAddress + ", ToAddress=" + ToAddress + ", subject=" + subject
				+ ", message=" + message + ", createdDate=" + createdDate + ", sendDate=" + sendDate + ", errors="
				+ errors + "]";
	}

}
