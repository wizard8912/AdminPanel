package pl.pniedziela.user;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import pl.pniedziela.annotations.FieldMatch;

@FieldMatch(first = "password", second = "passwordVerify", message = "{user.SignUp.PasswordNotMatch}")
public class UserDto {

	private long id;
	@NotEmpty(message = "{user.SignUp.NicknameEmpty}")
	@Size(min = 5, max = 30, message = "{user.SignUp.NicknameSize} 5-30")
	private String nickname;
	private String firstname;
	private String lastname;
	@Email(message = "{user.SignUp.EmailNotValid}")
	@NotEmpty(message = "{user.SignUp.EmailEmpty}")
	private String email;
	@Size(min = 6, max = 30, message = "{user.SignUp.PasswordSize} 6-30")
	@Pattern.List({ @Pattern(regexp = "(?=.*[0-9]).+", message = "{user.SignUp.PasswordDigit}"),
			@Pattern(regexp = "(?=.*[a-z]).+", message = "{user.SignUp.PasswordLowerLetter}"),
			@Pattern(regexp = "(?=.*[A-Z]).+", message = "{user.SignUp.PasswordUpperLetter}"),
			@Pattern(regexp = "(?=\\S+$).+", message = "{user.SignUp.PasswordWhiteSpace}") })
	private String password;
	private String passwordVerify;
	private String country;
	private String city;
	@DateTimeFormat(iso = ISO.DATE)
	private Date birthdate;
	@Pattern(regexp = "(^$|[0-9]{0,12})", message = "{user.SignUp.PhoneNumberNotValid}")
	private String phonenumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "UserDto [nickname=" + nickname + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", password=" + password + ", passwordVerify=" + passwordVerify + ", country=" + country
				+ ", city=" + city + ", birthdate=" + birthdate + ", phonenumber=" + phonenumber + "]";
	}
}
