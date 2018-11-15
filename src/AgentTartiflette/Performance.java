package AgentTartiflette;

public class Performance {

	private Integer performance;

	public Performance() {
		this.performance = new Integer(0);
	}

	public void add(int n) {
		this.performance += n;
		
	}

}
