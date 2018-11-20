package AgentTartiflette;
import System.Interface;

public class Capteur {

	private String caracteristiqueCase;

	public Capteur() {
		this.caracteristiqueCase = null;
	}

	/**
	 * Sauvegarde dans l'attribut caracteristiqueCase la caractérisitque de la case
	 * x, y
	 * 
	 * @param environnement
	 * @param x
	 * @param y
	 */
	public void capterCaseTartiflette(int x, int y) {
		String caracteristiqueCase = Interface.environnement.getCase(x, y).getCaracteristique();
		if (caracteristiqueCase.equals("caca") || caracteristiqueCase.equals("vent")
				|| caracteristiqueCase.equals("Porte") || caracteristiqueCase.equals("Vide")
			|| caracteristiqueCase.equals("Crevasse") || caracteristiqueCase.equals("monstre")) {
			this.caracteristiqueCase = caracteristiqueCase;
		} else
			System.err.println("La caratéristique de la case n'est pas égale à 'caca', 'vent', 'Porte', 'Crevasse', 'monste', ou à 'Vide'.");
		}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Capteur))
			return false;
		Capteur other = (Capteur) obj;
		if (caracteristiqueCase == null) {
			if (other.caracteristiqueCase != null)
				return false;
		} else if (!caracteristiqueCase.equals(other.caracteristiqueCase))
			return false;
		return true;
	}

	public String getCaracteristiqueCase() {
		return caracteristiqueCase;
	}
	
	public void setCaracteristiqueCase(String value) {
		caracteristiqueCase=value;
	}

	@Override
	public String toString() {
		return "Capteur [caracteristiqueCase=" + caracteristiqueCase + "]";
	}
}