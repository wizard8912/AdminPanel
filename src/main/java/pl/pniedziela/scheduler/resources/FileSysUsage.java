package pl.pniedziela.scheduler.resources;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hyperic.sigar.FileSystemUsage;

@Entity(name = "FileSysUsageStats")
public class FileSysUsage {
	@Id
	@SequenceGenerator(name = "fileSys_id_seq", sequenceName = "fileSys_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "fileSys_id_seq")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private long avail;
	private double diskQueue;
	private long readBytes;
	private long diskReads;
	private double diskServiceTime;
	private long diskWriteBytes;
	private long files;
	private long free;
	private long freeFiles;
	private long total;
	private long used;
	private double usePercent;

	public FileSysUsage(FileSystemUsage fileSystemUsage) {

		this.date = new Date();
		this.avail = fileSystemUsage.getAvail();
		this.diskQueue = fileSystemUsage.getDiskQueue();
		this.readBytes = fileSystemUsage.getDiskReadBytes();
		this.diskReads = fileSystemUsage.getDiskReads();
		this.diskServiceTime = fileSystemUsage.getDiskServiceTime();
		this.diskWriteBytes = fileSystemUsage.getDiskWriteBytes();
		this.files = fileSystemUsage.getFiles();
		this.free = fileSystemUsage.getFree();
		this.freeFiles = fileSystemUsage.getFreeFiles();
		this.total = fileSystemUsage.getTotal();
		this.used = fileSystemUsage.getUsed();
		this.usePercent = fileSystemUsage.getUsePercent() * 100;
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public long getAvail() {
		return avail;
	}

	public double getDiskQueue() {
		return diskQueue;
	}

	public long getReadBytes() {
		return readBytes;
	}

	public long getDiskReads() {
		return diskReads;
	}

	public double getDiskServiceTime() {
		return diskServiceTime;
	}

	public long getDiskWriteBytes() {
		return diskWriteBytes;
	}

	public long getFiles() {
		return files;
	}

	public long getFree() {
		return free;
	}

	public long getFreeFiles() {
		return freeFiles;
	}

	public long getTotal() {
		return total;
	}

	public long getUsed() {
		return used;
	}

	public double getUsePercent() {
		return usePercent;
	}

	@Override
	public String toString() {
		return "FileSysUsage [id=" + id + ", date=" + date + ", avail=" + avail + ", diskQueue=" + diskQueue
				+ ", readBytes=" + readBytes + ", diskReads=" + diskReads + ", diskServiceTime=" + diskServiceTime
				+ ", diskWriteBytes=" + diskWriteBytes + ", files=" + files + ", free=" + free + ", freeFiles="
				+ freeFiles + ", total=" + total + ", used=" + used + ", usePercent=" + usePercent + "]";
	}
}
