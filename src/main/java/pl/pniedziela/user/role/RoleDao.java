package pl.pniedziela.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

	public Role findById(long id);

	public Role findByName(String name);

	public <S extends Role> S save(S entity);
}
