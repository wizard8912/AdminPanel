package pl.pniedziela.sessions;

public class BrowserStat {

	private String browserName;
	private long usages;

	public BrowserStat(String browserName, long usages) {
		super();
		this.browserName = browserName;
		this.usages = usages;
	}

	public String getBrowserName() {
		return browserName;
	}

	public long getUsages() {
		return usages;
	}

	@Override
	public String toString() {
		return "BrowserStat [browserName=" + browserName + ", usages=" + usages + "]";
	}
}
