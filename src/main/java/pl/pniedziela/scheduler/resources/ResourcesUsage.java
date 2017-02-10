package pl.pniedziela.scheduler.resources;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResourcesUsage {

	@JsonIgnore
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date date;
	private double memUsage;
	private double cpuUsage;
	private double fileSysUsage;

	public ResourcesUsage(long id, Date date, double cpuUsage, double memUsage) {
		super();
		this.id = id;
		this.date = date;
		this.memUsage = memUsage;
		this.cpuUsage = cpuUsage;
	}

	public ResourcesUsage(long id, Date date, double fileSysUsage) {
		super();
		this.id = id;
		this.date = date;
		this.fileSysUsage = fileSysUsage;
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public double getMemUsage() {
		return memUsage;
	}

	public double getCpuUsage() {
		return cpuUsage;
	}

	public double getFileSysUsage() {
		return fileSysUsage;
	}
}
