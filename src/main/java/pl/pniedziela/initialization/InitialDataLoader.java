package pl.pniedziela.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import pl.pniedziela.sessions.SessionsDetailsService;
import pl.pniedziela.user.role.Role;
import pl.pniedziela.user.role.RoleService;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	RoleService roleService;
	@Autowired
	SessionsDetailsService sessionsService;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		createRoleIfNotFound("ROLE_USER");
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_SUPERADMIN");
		sessionsService.destroyAllActiveSessions();
	}

	private Role createRoleIfNotFound(String name) {

		Role role = roleService.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleService.saveRole(role);
		}
		return role;
	}

}
