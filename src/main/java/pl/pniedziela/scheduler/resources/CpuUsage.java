package pl.pniedziela.scheduler.resources;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hyperic.sigar.Cpu;

@Entity(name = "CpuUsageStats")
public class CpuUsage {

	@Id
	@SequenceGenerator(name = "cpuUsage_id_seq", sequenceName = "cpuUsage_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cpuUsage_id_seq")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private long idle;
	private long irq;
	private long nice;
	private long softIrq;
	private long stolen;
	private long sys;
	private long total;
	private long usertime;
	private long wait;
	private double usagePercent;

	public CpuUsage(Cpu cpu, double usagePercent) {
		this.date = new Date();
		this.idle = cpu.getIdle();
		this.irq = cpu.getIrq();
		this.nice = cpu.getNice();
		this.softIrq = cpu.getSoftIrq();
		this.stolen = cpu.getStolen();
		this.sys = cpu.getSys();
		this.total = cpu.getTotal();
		this.usertime = cpu.getUser();
		this.wait = cpu.getWait();
		this.usagePercent = usagePercent;
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public long getIdle() {
		return idle;
	}

	public long getIrq() {
		return irq;
	}

	public long getNice() {
		return nice;
	}

	public long getSoftIrq() {
		return softIrq;
	}

	public long getStolen() {
		return stolen;
	}

	public long getSys() {
		return sys;
	}

	public long getTotal() {
		return total;
	}

	public long getUser() {
		return usertime;
	}

	public long getWait() {
		return wait;
	}

	public double getUsagePercent() {
		return usagePercent;
	}

}
