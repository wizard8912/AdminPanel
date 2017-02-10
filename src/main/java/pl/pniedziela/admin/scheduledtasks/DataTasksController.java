package pl.pniedziela.admin.scheduledtasks;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.scheduler.email.ScheduledEmailTask;
import pl.pniedziela.scheduler.email.ScheduledEmailTaskService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
public class DataTasksController {

	@Autowired
	ScheduledEmailTaskService taskService;

	@RequestMapping(value = "/emailTask", method = RequestMethod.GET)
	public DataTablesOutput<ScheduledEmailTask> getEmailTaskData(@Valid DataTablesInput input) {
		DataTablesOutput<ScheduledEmailTask> listTask;
		listTask = taskService.findAll(input);
		return listTask;
	}
}
