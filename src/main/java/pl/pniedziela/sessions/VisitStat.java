package pl.pniedziela.sessions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VisitStat {

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy")
	private Date date;
	private long uniqueVisit;
	private long generalVisit;

	public VisitStat(Date date, long uniqueVisit, long generalVisit) {
		super();
		this.date = date;
		this.uniqueVisit = uniqueVisit;
		this.generalVisit = generalVisit;
	}

	public Date getDate() {
		return date;
	}

	public long getUniqueVisit() {
		return uniqueVisit;
	}

	public long getGeneralVisit() {
		return generalVisit;
	}

}
