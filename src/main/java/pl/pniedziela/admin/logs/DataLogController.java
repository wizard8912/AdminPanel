package pl.pniedziela.admin.logs;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataLogController {
	@Autowired
	LogService logService;

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public DataTablesOutput<Log> getLogsData(@Valid DataTablesInput input) {
		DataTablesOutput<Log> listLog;
		listLog = logService.findAll(input);
		return listLog;
	}
}
