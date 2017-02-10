package pl.pniedziela.bans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import pl.pniedziela.user.User;

@Entity
public class Ban {

	@Id
	@SequenceGenerator(name = "bans_id_seq", sequenceName = "bans_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "bans_id_seq")
	private long id;
	private boolean active;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date dateFrom;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date dateTo;
	@ManyToOne(targetEntity = User.class)
	private User user;
	private String reason;
	@ManyToOne(targetEntity = User.class)
	private User admin;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date lastModifiedDate;
	@ManyToOne(targetEntity = User.class)
	private User lastModifiedAdmin;
	private String ipAddress;

	public Ban() {
		this.active = true;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public long getId() {
		return id;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public User getLastModifiedAdmin() {
		return lastModifiedAdmin;
	}

	public void setLastModifiedAdmin(User lastModifiedAdmin) {
		this.lastModifiedAdmin = lastModifiedAdmin;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Ban [id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", user=" + user.getUsername()
				+ ", reason=" + reason + "]";
	}
}
