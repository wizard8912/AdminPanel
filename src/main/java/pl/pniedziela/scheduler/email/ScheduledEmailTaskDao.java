package pl.pniedziela.scheduler.email;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ScheduledEmailTaskDao extends DataTablesRepository<ScheduledEmailTask, Long> {

	public <S extends ScheduledEmailTask> S save(S entity);
}
