package pl.pniedziela.scheduler.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourcesUsageService {

	@Autowired
	CpuUsageDao cpuUsageDao;

	public List<ResourcesUsage> getResourcesUsage() {

		return cpuUsageDao.getResourcesUsage();
	}

	public List<ResourcesUsage> getLastResourceUsage() {
		Pageable topOne = new PageRequest(0, 1, Direction.DESC, "id");
		return cpuUsageDao.getLastResourceUsage(topOne).getContent();
	}

	public List<ResourcesUsage> getFileSysUsage() {

		return cpuUsageDao.getFileSysUsage();
	}

	public List<ResourcesUsage> getLastFileSysUsage() {

		Pageable topOne = new PageRequest(0, 1, Direction.DESC, "id");
		return cpuUsageDao.getLastFileSysUsage(topOne).getContent();
	}
}
