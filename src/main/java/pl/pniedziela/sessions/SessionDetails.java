package pl.pniedziela.sessions;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import pl.pniedziela.user.User;

@Entity
public class SessionDetails {

	@Id
	@SequenceGenerator(name = "sessionDetails_id_seq", sequenceName = "sessionDetails_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sessionDetails_id_seq")
	private long id;
	private String sessionID;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date destroyedDate;
	private String ipAddress;
	private String userBrowser;
	private String userBrowserVersion;
	private String locale;
	private String userOS;
	private double displayWidth;
	private double displayHeight;
	@ManyToOne(targetEntity = User.class)
	private User user;

	public long getId() {
		return id;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = new Date(createdDate);
	}

	public Date getDestroyedDate() {
		return destroyedDate;
	}

	public void setDestroyedDate(Date destroyedDate) {
		this.destroyedDate = destroyedDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserBrowser() {
		return userBrowser;
	}

	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}

	public String getUserBrowserVersion() {
		return userBrowserVersion;
	}

	public void setUserBrowserVersion(String userBrowserVersion) {
		this.userBrowserVersion = userBrowserVersion;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getUserOS() {
		return userOS;
	}

	public void setUserOS(String userOS) {
		this.userOS = userOS;
	}

	public double getDisplayWidth() {
		return displayWidth;
	}

	public void setDisplayWidth(double displayWidth) {
		this.displayWidth = displayWidth;
	}

	public double getDisplayHeight() {
		return displayHeight;
	}

	public void setDisplayHeight(double displayHeight) {
		this.displayHeight = displayHeight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SessionDetails [id=" + id + ", sessionID=" + sessionID + ", createdDate=" + createdDate
				+ ", destroyedDate=" + destroyedDate + ", ipAddress=" + ipAddress + ", userBrowser=" + userBrowser
				+ ", userBrowserVersion=" + userBrowserVersion + ", locale=" + locale + ", userOS=" + userOS
				+ ", displayWidth=" + displayWidth + ", displayHeight=" + displayHeight + ", user=" + user + "]";
	}

}
