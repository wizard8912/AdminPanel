package pl.pniedziela.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import pl.pniedziela.bans.Ban;
import pl.pniedziela.user.role.Role;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
	private long id;
	@Column(unique = true, nullable = false)
	private String nickname;
	private String firstname;
	private String lastname;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	private String country;
	private String city;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy")
	private Date birthdate;
	private int phonenumber;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm")
	private Date registerdate;
	@JsonIgnore
	private String activateLink;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm")
	private Date activateDate;
	@Column(columnDefinition = "boolean default false")
	@JsonIgnore
	private boolean deletedAccount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedDate;
	private String deletedBy;
	@JsonIgnore
	private String forgetPassLink;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm")
	private Date forgetPassDate;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
	private Collection<Role> roles;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Collection<Ban> bans;
	@Column(unique = true, nullable = true)
	private String googleID;
	private String googlePassword;
	private String googleHashedPassword;

	public User() {
		this.registerdate = new Date();
		this.activateLink = UUID.randomUUID().toString();
		this.deletedAccount = false;
		roles = new ArrayList<Role>();
	}

	public User(long id, String nickname, String firstname, String lastname, String email, String password,
			String country, String city, Date birthdate, int phonenumber, Date registerdate, String activateLink,
			Date activateDate, boolean deletedAccount, Date deletedDate, String deletedBy, String forgetPassLink,
			Date forgetPassDate, Collection<Role> roles, Collection<Ban> bans, String googleID, String googlePassword,
			String googleHashedPassword) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.country = country;
		this.city = city;
		this.birthdate = birthdate;
		this.phonenumber = phonenumber;
		this.registerdate = registerdate;
		this.activateLink = activateLink;
		this.activateDate = activateDate;
		this.deletedAccount = deletedAccount;
		this.deletedDate = deletedDate;
		this.deletedBy = deletedBy;
		this.forgetPassLink = forgetPassLink;
		this.forgetPassDate = forgetPassDate;
		this.roles = roles;
		this.bans = bans;
		this.googleID = googleID;
		this.googlePassword = googlePassword;
		this.googleHashedPassword = googleHashedPassword;
	}

	public User(String googleAuthID, String googleAuthEmail, String googleAuthFirstName, String googleAuthLastName) {
		this.email = googleAuthEmail;
		this.nickname = googleAuthEmail.substring(0, googleAuthEmail.indexOf("@"));
		if (this.nickname.length() > 15) {
			this.nickname = this.nickname.substring(0, 15);
		}
		this.firstname = googleAuthFirstName;
		this.lastname = googleAuthLastName;
		this.googleID = googleAuthID;
		this.registerdate = new Date();
		this.activateDate = new Date();
		this.deletedAccount = false;
		roles = new ArrayList<Role>();
	}

	public void setGooglePassword(String googlePassword) {
		if (this.googlePassword == null) {
			this.googlePassword = googlePassword;
		}
	}

	public String getGooglePassword() {
		return googlePassword;
	}

	public String getGoogleHashedPassword() {
		return googleHashedPassword;
	}

	public void setGoogleHashedPassword(String googleHashedPassword) {
		this.googleHashedPassword = googleHashedPassword;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void clearRole() {
		this.roles = new ArrayList<Role>();
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
		this.email = email.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}

	public boolean isDeletedAccount() {
		return deletedAccount;
	}

	public void setDeletedAccount(boolean deletedAccount) {
		this.deletedAccount = deletedAccount;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public long getId() {
		return id;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public String getActivateLink() {
		return activateLink;
	}

	public void setActivateLink(String activateLink) {
		this.activateLink = activateLink;
	}

	public String getForgetPassLink() {
		return forgetPassLink;
	}

	public void setForgetPassLink(String forgetPassLink) {
		this.forgetPassLink = forgetPassLink;
	}

	public Date getForgetPassDate() {
		return forgetPassDate;
	}

	public String getgoogleID() {
		return googleID;
	}

	public void setgoogleID(String googleID) {
		this.googleID = googleID;
	}

	public void setForgetPassDate(Date forgetPassDate) {
		this.forgetPassDate = forgetPassDate;
	}

	public Collection<Ban> getBans() {
		return bans;
	}

	public void setBans(Collection<Ban> bans) {
		this.bans = bans;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", password=" + password + ", country=" + country + ", city=" + city
				+ ", birthdate=" + birthdate + ", phonenumber=" + phonenumber + ", registerdate=" + registerdate
				+ ", activateLink=" + activateLink + ", activateDate=" + activateDate + ", deletedAccount="
				+ deletedAccount + ", deletedDate=" + deletedDate + ", forgetPassLink=" + forgetPassLink
				+ ", forgetPassDate=" + forgetPassDate + ", roles=" + roles + ", bans=" + bans + "]";
	}

	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}

	@JsonIgnore
	public String getUsername() {

		return nickname;
	}

	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	public boolean isAccountNonLocked() {

		for (Ban ban : bans) {
			if (!ban.isActive()) {
				continue;
			}

			DateTime bannedTo = new DateTime(ban.getDateTo());

			if (bannedTo.isAfterNow()) {
				return false;
			}
		}

		return true;
	}

	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	public boolean isEnabled() {

		if (deletedAccount) {
			return false;
		}

		if (activateLink == null && activateDate != null) {
			return true;
		} else {
			return false;
		}
	}

	@JsonIgnore
	public Ban getLatestActiveBan() {

		Ban ret = null;
		for (Ban ban : bans) {
			if (!ban.isActive()) {
				continue;
			}

			if (ret == null) {
				ret = ban;
			} else if (new DateTime(ban.getDateTo()).isAfter(new DateTime(ret.getDateTo()))) {
				ret = ban;
			}
		}

		return ret;
	}

	@JsonIgnore
	public boolean isAdmin() {
		for (Role role : roles) {
			if (role.getName().equals("ROLE_ADMIN") || role.getName().equals("ROLE_SUPERADMIN")) {
				return true;
			}
		}

		return false;
	}

	@JsonIgnore
	public boolean isSuperAdmin() {
		for (Role role : roles) {
			if (role.getName().equals("ROLE_SUPERADMIN")) {
				return true;
			}
		}

		return false;
	}
}
