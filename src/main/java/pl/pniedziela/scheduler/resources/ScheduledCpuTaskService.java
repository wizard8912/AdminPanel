package pl.pniedziela.scheduler.resources;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableScheduling()
@EnableAsync
@Transactional
public class ScheduledCpuTaskService {

	@Autowired
	Sigar sigar;
	@Autowired
	CpuUsageDao cpuUsageDao;
	@Autowired
	MemUsageDao memUsageDao;
	@Autowired
	FileSysUsageDao fileSysUsageDao;
/*
	@Scheduled(cron = "0 * * * * *")
	public void everyMinuteTask() {
		System.out.println("ScheduledCpuTaskService: everyMinuteTask");
		getAndSaveCpuStats();
		getAndSaveMemoryStats();
	}

	@Scheduled(cron = "0 0 * * * *")
	public void everyHourTask() {
		System.out.println("ScheduledCpuTaskService: everyHourTask");
		getAndSaveFileSystemStats();
	}*/

	@Scheduled(cron = "0 * * * * *")
	@Async
	private void getAndSaveCpuStats() {
		System.out.println("ScheduledCpuTaskService: getAndSaveCpuStats1");

		try {
			System.out.println("ScheduledCpuTaskService: getAndSaveCpuStats2");
			Cpu cpu = sigar.getCpu();
			CpuUsage cpuUsage = new CpuUsage(cpu, sigar.getCpuPerc().getCombined() * 100);
			cpuUsageDao.save(cpuUsage);
			System.out.println("ScheduledCpuTaskService: getAndSaveCpuStats3");
		} catch (SigarException e) {
			System.out.println("ScheduledCpuTaskService: getAndSaveCpuStats4");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 * * * * *")
	@Async
	private void getAndSaveMemoryStats() {
		System.out.println("ScheduledCpuTaskService: getAndSaveMemoryStats1");

		try {
			System.out.println("ScheduledCpuTaskService: getAndSaveMemoryStats2");
			Mem mem = sigar.getMem();
			MemUsage memUsage = new MemUsage(mem);
			memUsageDao.save(memUsage);
			System.out.println("ScheduledCpuTaskService: getAndSaveMemoryStats3");
		} catch (SigarException e) {
			System.out.println("ScheduledCpuTaskService: getAndSaveMemoryStats4");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0 * * * *")
	@Async
	private void getAndSaveFileSystemStats() {
		System.out.println("ScheduledCpuTaskService: ");
		try {
			System.out.println("ScheduledCpuTaskService: ");
			FileSystem[] fileSystemList = sigar.getFileSystemList();
			FileSystemUsage fileSystemUsage = sigar.getFileSystemUsage(fileSystemList[0].toString());

			FileSysUsage fsUsage = new FileSysUsage(fileSystemUsage);
			fileSysUsageDao.save(fsUsage);
			System.out.println("ScheduledCpuTaskService: ");
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
