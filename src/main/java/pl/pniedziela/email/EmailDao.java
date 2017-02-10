package pl.pniedziela.email;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface EmailDao extends DataTablesRepository<Email, Long> {

	public <S extends Email> S save(S entity);

	public List<Email> findBySendDateIsNull();
}
