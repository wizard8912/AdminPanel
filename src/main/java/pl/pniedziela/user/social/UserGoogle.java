package pl.pniedziela.user.social;

public class UserGoogle {

	private String googleAuthEmail;
	private String googleAuthID;
	private String googleAuthFirstName;
	private String googleAuthLastName;
	private String googleAuthImageUrl;

	public String getGoogleAuthEmail() {
		return googleAuthEmail;
	}

	public void setGoogleAuthEmail(String googleAuthEmail) {
		this.googleAuthEmail = googleAuthEmail;
	}

	public String getGoogleAuthID() {
		return googleAuthID;
	}

	public void setGoogleAuthID(String googleAuthID) {
		this.googleAuthID = googleAuthID;
	}

	public String getGoogleAuthFirstName() {
		return googleAuthFirstName;
	}

	public void setGoogleAuthFirstName(String googleAuthFirstName) {
		this.googleAuthFirstName = googleAuthFirstName;
	}

	public String getGoogleAuthLastName() {
		return googleAuthLastName;
	}

	public void setGoogleAuthLastName(String googleAuthLastName) {
		this.googleAuthLastName = googleAuthLastName;
	}

	public String getGoogleAuthImageUrl() {
		return googleAuthImageUrl;
	}

	public void setGoogleAuthImageUrl(String googleAuthImageUrl) {
		this.googleAuthImageUrl = googleAuthImageUrl;
	}

	@Override
	public String toString() {
		return "UserGoogle [googleAuthEmail=" + googleAuthEmail + ", googleAuthID=" + googleAuthID
				+ ", googleAuthFirstName=" + googleAuthFirstName + ", googleAuthLastName=" + googleAuthLastName
				+ ", googleAuthImageUrl=" + googleAuthImageUrl + "]";
	}
}
