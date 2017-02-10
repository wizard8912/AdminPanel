package pl.pniedziela.admin.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.scheduler.resources.ResourcesUsage;
import pl.pniedziela.scheduler.resources.ResourcesUsageService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataResourcesController {

	@Autowired
	ResourcesUsageService resourcesUsageService;

	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public List<ResourcesUsage> getResourcesData() {

		List<ResourcesUsage> resourcesUsage = resourcesUsageService.getResourcesUsage();
		return resourcesUsage;
	}

	@RequestMapping(value = "/resourcesfs", method = RequestMethod.GET)
	public List<ResourcesUsage> getFileSysData() {

		List<ResourcesUsage> fileSysUsage = resourcesUsageService.getFileSysUsage();
		return fileSysUsage;
	}
}
