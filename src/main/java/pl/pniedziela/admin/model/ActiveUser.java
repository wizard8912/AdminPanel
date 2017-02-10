package pl.pniedziela.admin.model;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import pl.pniedziela.user.User;

public class ActiveUser {

	private User user;
	private Date date;
	private int inactiveTime;
	private String inactiveTimeStr;

	public ActiveUser(User user, Date date) {
		this.user = user;
		setDate(date);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		DateTime now = new DateTime();
		DateTime lastActive = new DateTime(date);

		if (Minutes.minutesBetween(lastActive, now).getMinutes() < 60) {
			this.inactiveTime = Minutes.minutesBetween(lastActive, now).getMinutes();
			this.inactiveTimeStr = "min";
		} else if (Hours.hoursBetween(lastActive, now).getHours() < 24) {
			this.inactiveTime = Hours.hoursBetween(lastActive, now).getHours();
			this.inactiveTimeStr = "h";
		} else {
			this.inactiveTime = Days.daysBetween(lastActive, now).getDays();
			this.inactiveTimeStr = "m";
		}
	}

	public int getInactiveTime() {
		return inactiveTime;
	}

	public String getInactiveTimeStr() {
		return inactiveTimeStr;
	}

	@Override
	public String toString() {
		return "ActiveUser [user=" + user + ", date=" + date + "]";
	}

}
