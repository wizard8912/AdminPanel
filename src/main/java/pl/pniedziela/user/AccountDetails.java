package pl.pniedziela.user;

import java.util.Date;

public class AccountDetails {

	private int loginCount;
	private int failedLoginCount;
	private Date lastLogin;
	private Date lastFailedLogin;

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getFailedLoginCount() {
		return failedLoginCount;
	}

	public void setFailedLoginCount(int failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastFailedLogin() {
		return lastFailedLogin;
	}

	public void setLastFailedLogin(Date lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}
}
