package pl.pniedziela.admin.operations;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.operations.Operation;
import pl.pniedziela.operations.OperationService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataOperationController {

	@Autowired
	OperationService operationService;

	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	public DataTablesOutput<Operation> getOperationsData(@Valid DataTablesInput input) {
		DataTablesOutput<Operation> listOperation;
		listOperation = operationService.findAll(input);
		return listOperation;
	}
}
