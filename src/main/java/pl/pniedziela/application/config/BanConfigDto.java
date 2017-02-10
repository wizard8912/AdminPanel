package pl.pniedziela.application.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BanConfigDto {

	private boolean banForIp;
	private boolean banForUsr;
	@NotNull(message = "{admin.BanConfig.Attemps.NotEmpty}")
	@Min(value = 1, message = "{admin.BanConfig.Attemps.Min}")
	private int banLoginAttempsUsr;
	@NotNull(message = "{admin.BanConfig.Attemps.NotEmpty}")
	@Min(value = 1, message = "{admin.BanConfig.Attemps.Min}")
	private int banLoginAttempsIp;
	@NotNull(message = "{admin.BanConfig.CheckDays.NotEmpty}")
	private int banCheckDays;
	@NotNull(message = "{admin.BanConfig.CheckHours.NotEmpty}")
	@Max(value = 23, message = "{admin.BanConfig.CheckHours.Max}")
	private int banCheckHours;
	@NotNull(message = "{admin.BanConfig.CheckMinutes.NotEmpty}")
	@Max(value = 59, message = "{admin.BanConfig.CheckMinutes.Max}")
	private int banCheckMinutes;
	@NotNull(message = "{admin.BanConfig.ForDays.NotEmpty}")
	private int banForDays;
	@NotNull(message = "{admin.BanConfig.ForHours.NotEmpty}")
	@Max(value = 23, message = "{admin.BanConfig.ForHours.Max}")
	private int banForHours;
	@NotNull(message = "{admin.BanConfig.ForMinutes.NotEmpty}")
	@Max(value = 59, message = "{admin.BanConfig.ForMinutes.Max}")
	private int banForMinutes;

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

	public int getBanCheckDays() {
		return banCheckDays;
	}

	public void setBanCheckDays(int banCheckDays) {
		this.banCheckDays = banCheckDays;
	}

	public int getBanCheckHours() {
		return banCheckHours;
	}

	public void setBanCheckHours(int banCheckHours) {
		this.banCheckHours = banCheckHours;
	}

	public int getBanCheckMinutes() {
		return banCheckMinutes;
	}

	public void setBanCheckMinutes(int banCheckMinutes) {
		this.banCheckMinutes = banCheckMinutes;
	}

	public int getBanForDays() {
		return banForDays;
	}

	public void setBanForDays(int banForDays) {
		this.banForDays = banForDays;
	}

	public int getBanForHours() {
		return banForHours;
	}

	public void setBanForHours(int banForHours) {
		this.banForHours = banForHours;
	}

	public int getBanForMinutes() {
		return banForMinutes;
	}

	public void setBanForMinutes(int banForMinutes) {
		this.banForMinutes = banForMinutes;
	}

	@Override
	public String toString() {
		return "BanConfigDto [banForIp=" + banForIp + ", banForUsr=" + banForUsr + ", banLoginAttempsUsr="
				+ banLoginAttempsUsr + ", banLoginAttempsIp=" + banLoginAttempsIp + ", banCheckDays=" + banCheckDays
				+ ", banCheckHours=" + banCheckHours + ", banCheckMinutes=" + banCheckMinutes + ", banForDays="
				+ banForDays + ", banForHours=" + banForHours + ", banForMinutes=" + banForMinutes + "]";
	}

}
