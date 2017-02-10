package pl.pniedziela.scheduler.resources;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CpuUsageDao extends JpaRepository<CpuUsage, Long> {

	public <S extends CpuUsage> S save(S entity);

	@Query("SELECT new pl.pniedziela.scheduler.resources.ResourcesUsage(c.id, c.date, c.usagePercent, m.usedPercent) FROM CpuUsageStats c JOIN memUsageStats m on m.id = c.id")
	public List<ResourcesUsage> getResourcesUsage();

	@Query("SELECT new pl.pniedziela.scheduler.resources.ResourcesUsage(c.id, c.date, c.usagePercent, m.usedPercent) FROM CpuUsageStats c JOIN memUsageStats m on m.id = c.id")
	public Page<ResourcesUsage> getLastResourceUsage(Pageable pageable);

	@Query("SELECT new pl.pniedziela.scheduler.resources.ResourcesUsage(f.id, f.date, f.usePercent) FROM FileSysUsageStats f")
	public List<ResourcesUsage> getFileSysUsage();

	@Query("SELECT new pl.pniedziela.scheduler.resources.ResourcesUsage(f.id, f.date, f.usePercent) FROM FileSysUsageStats f")
	public Page<ResourcesUsage> getLastFileSysUsage(Pageable pageable);
}
