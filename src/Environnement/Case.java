package Environnement;



public class Case {



	private boolean vent;

	private boolean monstreGayPride;

	private boolean cacaLicorne;

	private boolean crevasse;

	private boolean portailMagique;



	public Case(boolean vent, boolean monstreGayPride, boolean cacaLicorne, boolean crevasse) {

		this.setVent(vent);

		this.setMonstreGayPride(monstreGayPride);

		this.setCacaLicorne(cacaLicorne);

		this.setCrevasse(crevasse);

	}



	public boolean getVent() {

		return this.vent;

	}



	public void setVent(boolean vent) {

		this.vent = vent;

	}



	public boolean getMonstreGayPride() {

		return monstreGayPride;

	}



	public void setMonstreGayPride(boolean monstreGayPride) {

		this.monstreGayPride = monstreGayPride;

	}



	public boolean getCacaLicorne() {

		return cacaLicorne;

	}



	public void setCacaLicorne(boolean cacaLicorne) {

		this.cacaLicorne = cacaLicorne;

	}



	public boolean getCrevasse() {

		return crevasse;

	}



	public void setCrevasse(boolean crevasse) {

		this.crevasse = crevasse;

	}



	public boolean getPortailMagique() {

		return portailMagique;

	}



	public void setPortailMagique(boolean portailMagique) {

		this.portailMagique = portailMagique;

	}



	public boolean Vide() {

		if (!vent && !crevasse && !monstreGayPride && !cacaLicorne && !portailMagique)

			return true;

		else

			return false;

	}



	public String getCaracteristique() {

		String resultat = null;

		if (vent && !monstreGayPride && !cacaLicorne && !crevasse && !portailMagique) {

			resultat = "vent"; // vent

		} else if (!vent && !monstreGayPride && cacaLicorne && !crevasse && !portailMagique) {

			resultat = "caca";// caca

		} else if (!vent && !monstreGayPride && !cacaLicorne && !crevasse && portailMagique) {

			resultat = "Porte";// Porte magique

		} else if (!vent && monstreGayPride && !cacaLicorne && !crevasse && !portailMagique) {

			resultat = "monstre";// Monstre

		} else if (!vent && !monstreGayPride && !cacaLicorne && crevasse && !portailMagique) {

			resultat = "Crevasse";// Crevasse

		} else if (!vent && !monstreGayPride && !cacaLicorne && !crevasse && !portailMagique) {

			resultat = "Vide";// Case vide

		}

		return resultat;

	}

}