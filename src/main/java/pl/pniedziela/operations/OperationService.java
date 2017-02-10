package pl.pniedziela.operations;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.admin.model.ActiveUser;
import pl.pniedziela.user.User;

@Service
@Transactional
public class OperationService {

	@Autowired
	OperationDao operationDao;

	@Async
	public void saveOperation(Operation operation) {

		operationDao.save(operation);
	}

	public DataTablesOutput<Operation> findAll(DataTablesInput input) {

		return operationDao.findAll(input);
	}

	public List<ActiveUser> getListOfActiveUsers() {

		DateTime now = new DateTime();
		Date oneDayAgo = now.minusDays(1).toDate();
		return operationDao.getListOfActiveUsers(oneDayAgo);
	}
}
