package pl.pniedziela.application.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ApplicationConfig {

	@Id
	@SequenceGenerator(name = "appConfig_id_seq", sequenceName = "appConfig_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "appConfig_id_seq")
	private long id;
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean banForIp;
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean banForUsr;
	private int banLoginAttempsUsr;
	private int banLoginAttempsIp;
	private int banCheckSeconds;
	private int banForSeconds;

	public boolean isBanForIp() {
		return banForIp;
	}

	public void setBanForIp(boolean banForIp) {
		this.banForIp = banForIp;
	}

	public boolean isBanForUsr() {
		return banForUsr;
	}

	public void setBanForUsr(boolean banForUsr) {
		this.banForUsr = banForUsr;
	}

	public int getBanLoginAttempsUsr() {
		return banLoginAttempsUsr;
	}

	public void setBanLoginAttempsUsr(int banLoginAttempsUsr) {
		this.banLoginAttempsUsr = banLoginAttempsUsr;
	}

	public int getBanLoginAttempsIp() {
		return banLoginAttempsIp;
	}

	public void setBanLoginAttempsIp(int banLoginAttempsIp) {
		this.banLoginAttempsIp = banLoginAttempsIp;
	}

	public int getBanCheckSeconds() {
		return banCheckSeconds;
	}

	public void setBanCheckSeconds(int banCheckSeconds) {
		this.banCheckSeconds = banCheckSeconds;
	}

	public int getBanForSeconds() {
		return banForSeconds;
	}

	public void setBanForSeconds(int banForSeconds) {
		this.banForSeconds = banForSeconds;
	}

	@Override
	public String toString() {
		return "ApplicationConfig [id=" + id + ", banForIp=" + banForIp + ", banForUsr=" + banForUsr
				+ ", banLoginAttempsUsr=" + banLoginAttempsUsr + ", banLoginAttempsIp=" + banLoginAttempsIp
				+ ", banCheckSeconds=" + banCheckSeconds + ", banForSeconds=" + banForSeconds + "]";
	}

}
