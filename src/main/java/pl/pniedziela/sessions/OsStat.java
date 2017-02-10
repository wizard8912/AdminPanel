package pl.pniedziela.sessions;

public class OsStat {

	private String osName;
	private long usages;

	public OsStat(String osName, long usages) {
		super();
		this.osName = osName;
		this.usages = usages;
	}

	public String getOsName() {
		return osName;
	}

	public long getUsages() {
		return usages;
	}

}
