package pl.pniedziela.admin.emails;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.email.Email;
import pl.pniedziela.email.EmailService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataEmailController {
	@Autowired
	EmailService emailService;

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public DataTablesOutput<Email> getOperationsData(@Valid DataTablesInput input) {
		DataTablesOutput<Email> listEmail;
		listEmail = emailService.findAll(input);
		return listEmail;
	}
}
