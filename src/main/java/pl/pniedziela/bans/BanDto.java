package pl.pniedziela.bans;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import pl.pniedziela.annotations.NotEmptyAll;

@NotEmptyAll(first = "userId", second = "ipAddress", message = "{admin.Bans.NotEmptyUserIdAndIpAdress}")
public class BanDto {

	private int banType;
	private long userId;
	private String ipAddress;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull(message = "{admin.Bans.DateToEmpty}")
	private Date dateTo;
	@NotEmpty(message = "{admin.Bans.ReasonEmpty}")
	private String reason;

	public int getBanType() {
		return banType;
	}

	public void setBanType(int banType) {
		this.banType = banType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
