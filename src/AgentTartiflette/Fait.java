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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caracteristique == null) ? 0 : caracteristique.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Fait))
			return false;
		Fait other = (Fait) obj;
		if (caracteristique == null) {
			if (other.caracteristique != null)
				return false;
		} else if (!caracteristique.equals(other.caracteristique))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
