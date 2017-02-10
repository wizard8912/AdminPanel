package pl.pniedziela.admin.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataUserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public DataTablesOutput<User> getUsersData(@Valid DataTablesInput input) {
		DataTablesOutput<User> listUser;
		listUser = userService.findAllActiveUsers(input);
		return listUser;
	}

	@RequestMapping(value = "/deletedUser", method = RequestMethod.GET)
	public DataTablesOutput<User> getDeletedUsersData(@Valid DataTablesInput input) {
		DataTablesOutput<User> listUser;
		listUser = userService.findAllDeletedUsers(input);
		return listUser;
	}

	@RequestMapping(value = "/user/activate", method = RequestMethod.POST)
	public Map<String, String> activateUserAccount(@RequestParam Long userId) {

		Map<String, String> ret = new HashMap<String, String>();

		User userToActivate = userService.findById(userId);
		if (userToActivate.getActivateDate() != null) {
			ret.put("val", "false");
			ret.put("msg", "accountIsActive");
			return ret;
		}
		boolean complete = userService.activateAccountById(userId);
		ret.put("val", complete ? "true" : "false");
		return ret;
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public Map<String, String> deleteUserAccount(@RequestParam Long userId, HttpServletRequest request) {

		Map<String, String> ret = new HashMap<String, String>();
		boolean complete = userService.deleteAccount(userId, request.getRemoteUser());
		ret.put("val", complete ? "true" : "false");
		return ret;
	}

	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
	@RequestMapping(value = "/user/activationlink", method = RequestMethod.POST)
	public Map<String, String> sendActivationLink(@RequestParam Long userId) {

		User userToActivate = userService.findById(userId);
		Map<String, String> ret = new HashMap<String, String>();
		if (userToActivate.getActivateDate() != null) {
			ret.put("val", "false");
			ret.put("msg", "accountIsActive");
			return ret;
		}
		boolean complete = userService.sendActivationLink(userId);
		ret.put("val", complete ? "true" : "false");
		return ret;
	}

	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
	@RequestMapping(value = "/user/passlink", method = RequestMethod.POST)
	public Map<String, String> sendPassLink(@RequestParam Long userId) {

		boolean complete = userService.sendPassLinkById(userId);
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("val", complete ? "true" : "false");

		return ret;
	}

	@RequestMapping(value = "/user/restore", method = RequestMethod.POST)
	public Map<String, String> restoreAccount(@RequestParam Long userId) {

		boolean complete = userService.restoreAccount(userId);
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("val", complete ? "true" : "false");

		return ret;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void createUser() {

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String getUserData(@PathVariable int id) {
		return null;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable int id) {
		return null;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id) {

	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUsers() {

	}
}
