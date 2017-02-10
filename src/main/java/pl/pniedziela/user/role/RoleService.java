package pl.pniedziela.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleDao roleDao;

	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

	public void saveRole(Role role) {
		roleDao.save(role);
	}

	public Role findById(int roleId) {
		return roleDao.findById(roleId);
	}
}
