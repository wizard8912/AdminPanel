package pl.pniedziela.user.remindpass;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pl.pniedziela.annotations.FieldMatch;

@FieldMatch(first = "password", second = "passwordVerify", message = "{user.SignUp.PasswordNotMatch}")
public class NewPasswordDto {

	@Size(min = 6, max = 30, message = "{user.SignUp.PasswordSize} 6-30")
	@Pattern.List({ @Pattern(regexp = "(?=.*[0-9]).+", message = "{user.SignUp.PasswordDigit}"),
			@Pattern(regexp = "(?=.*[a-z]).+", message = "{user.SignUp.PasswordLowerLetter}"),
			@Pattern(regexp = "(?=.*[A-Z]).+", message = "{user.SignUp.PasswordUpperLetter}"),
			@Pattern(regexp = "(?=\\S+$).+", message = "{user.SignUp.PasswordWhiteSpace}") })
	private String password;
	private String passwordVerify;
	private String remindPassCode;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordVerify() {
		return passwordVerify;
	}

	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	public String getRemindPassCode() {
		return remindPassCode;
	}

	public void setRemindPassCode(String remindPassCode) {
		this.remindPassCode = remindPassCode;
	}

	@Override
	public String toString() {
		return "NewPasswordDto [password=" + password + ", passwordVerify=" + passwordVerify + ", remindPassCode="
				+ remindPassCode + "]";
	}
}
