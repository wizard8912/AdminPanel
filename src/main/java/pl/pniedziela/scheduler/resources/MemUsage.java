package pl.pniedziela.scheduler.resources;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hyperic.sigar.Mem;

@Entity(name = "memUsageStats")
public class MemUsage {

	@Id
	@SequenceGenerator(name = "memUsage_id_seq", sequenceName = "memUsage_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "memUsage_id_seq")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private long actualFree;
	private long actualUsed;
	private long free;
	private double freePercent;
	private long ram;
	private long total;
	private long used;
	private double usedPercent;

	public MemUsage(Mem mem) {
		this.date = new Date();
		this.actualFree = mem.getActualFree();
		this.actualUsed = mem.getActualUsed();
		this.free = mem.getFree();
		this.freePercent = mem.getFreePercent();
		this.ram = mem.getRam();
		this.total = mem.getTotal();
		this.used = mem.getUsed();
		this.usedPercent = mem.getUsedPercent();
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public long getActualFree() {
		return actualFree;
	}

	public long getActualUsed() {
		return actualUsed;
	}

	public long getFree() {
		return free;
	}

	public double getFreePercent() {
		return freePercent;
	}

	public long getRam() {
		return ram;
	}

	public long getTotal() {
		return total;
	}

	public long getUsed() {
		return used;
	}

	public double getUsedPercent() {
		return usedPercent;
	}
}
