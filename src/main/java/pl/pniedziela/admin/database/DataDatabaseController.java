package pl.pniedziela.admin.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.database.stat.ObjectSize;
import pl.pniedziela.database.stat.ObjectSizeService;
import pl.pniedziela.email.Email;
import pl.pniedziela.email.EmailService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataDatabaseController {
	@Autowired
	ObjectSizeService objectSizeService;

	@RequestMapping(value = "/tablesSize", method = RequestMethod.GET)
	public List<ObjectSize> getDatabaseData() {

		List<ObjectSize> allObjectsSizes = objectSizeService.getAllObjectsSizes();
		return allObjectsSizes;
	}
}
