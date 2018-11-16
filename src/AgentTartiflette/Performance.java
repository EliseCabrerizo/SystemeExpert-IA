package AgentTartiflette;

public class Performance {

	private Integer performance;

	public Performance() {
		this.performance = new Integer(0);
	}

	public void add(int n) {
		this.performance += n;
	}

	public Integer getPerformance() {
		return performance;
	}

	public void setPerformance(Integer performance) {
		this.performance = performance;
	}

	@Override
	public String toString() {
		return "Performance [performance = " + performance + "]";
	}

}