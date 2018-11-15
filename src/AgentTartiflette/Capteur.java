package AgentTartiflette;

import Environnement.Environnement;

public class Capteur {
	private String caracteristiqueCase;

	public Capteur() {
		this.caracteristiqueCase = null;
	}

	public void capterCaseTartiflette(Environnement environnement, int x, int y) {
		String caracteristiqueCase = environnement.getCase(x, y).getCaracteristique();
		if (caracteristiqueCase.equals("Odeur") || caracteristiqueCase.equals("Vent")
				|| caracteristiqueCase.equals("Lumiere") || caracteristiqueCase.equals("Vide")) {
			this.caracteristiqueCase = caracteristiqueCase;
		} else
			System.err
					.println("La caratéristique de la case n'est pas égale à 'Odeur', 'Vent', 'Lumiere', ou à 'Vide'.");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caracteristiqueCase == null) ? 0 : caracteristiqueCase.hashCode());
		return result;
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
}
