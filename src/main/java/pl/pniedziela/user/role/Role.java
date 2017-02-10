package pl.pniedziela.user.role;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;

import pl.pniedziela.user.User;

@Entity
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "roles_id_seq")
	private long id;
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Collection<User> users;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	public String getAuthority() {
		return name;
	}

}
