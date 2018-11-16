package AgentTartiflette;

public class Fait {
	private int x;
	private int y;
	private String caracteristique;

	/**
	 * @param x
	 * @param y
	 * @param caracteristique
	 */
	public Fait(int x, int y, String caracteristique) {
		super();
		this.x = x;
		this.y = y;
		this.caracteristique = caracteristique;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getCaracteristique() {
		return caracteristique;
	}

	public void setCaracteristique(String caracteristique) {
		this.caracteristique = caracteristique;
	}

	@Override
	public String toString() {
		return "Fait [x=" + x + ", y=" + y + ", caracteristique=" + caracteristique + "]";
	}

}
