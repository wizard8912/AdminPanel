package pl.pniedziela.scheduler.resources;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileSysUsageDao extends JpaRepository<FileSysUsage, Long> {

	public <S extends FileSysUsage> S save(S entity);
}
