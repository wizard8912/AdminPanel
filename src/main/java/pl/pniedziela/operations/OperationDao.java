package pl.pniedziela.operations;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import pl.pniedziela.admin.model.ActiveUser;

public interface OperationDao extends DataTablesRepository<Operation, Long> {

	public <S extends Operation> S save(S entity);

	@Query("SELECT new pl.pniedziela.admin.model.ActiveUser(u, max(o.operationDate)) FROM Operation o JOIN User u on o.remoteUser = u.nickname WHERE o.operationDate >= ?1  GROUP BY u ORDER BY max(o.operationDate) DESC")
	public List<ActiveUser> getListOfActiveUsers(Date dateFrom);
}
