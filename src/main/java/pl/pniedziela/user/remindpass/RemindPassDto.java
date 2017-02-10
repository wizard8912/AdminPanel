package pl.pniedziela.user.remindpass;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RemindPassDto {

	@Email(message = "{user.SignUp.EmailNotValid}")
	@NotEmpty(message = "{user.SignUp.EmailEmpty}")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RemindPassDto [email=" + email + "]";
	}
}
