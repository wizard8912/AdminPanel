package pl.pniedziela.logs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Log {

	@Id
	@SequenceGenerator(name = "logs_id_seq", sequenceName = "logs_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "logs_id_seq")
	private long id;
	private String username;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date date;
	@Enumerated(EnumType.ORDINAL)
	private LogTypes logType;
	private String ipAddress;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LogTypes getLogType() {
		return logType;
	}

	public void setLogType(LogTypes logType) {
		this.logType = logType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", username=" + username + ", date=" + date + ", logType=" + logType + "]";
	}
}
