package pl.pniedziela.admin.serverlogs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInfo {

	private long lastModifiedDate;
	private String name;
	private String size;

	public FileInfo(long lastModifiedDate, String name, long size) {
		super();
		this.lastModifiedDate = lastModifiedDate;
		this.name = name;
		this.size = Long.toString(size) + " B";
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(this.lastModifiedDate);
	}

}
