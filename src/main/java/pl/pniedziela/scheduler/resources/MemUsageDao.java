package pl.pniedziela.scheduler.resources;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemUsageDao extends JpaRepository<MemUsage, Long> {

	public <S extends MemUsage> S save(S entity);
}
