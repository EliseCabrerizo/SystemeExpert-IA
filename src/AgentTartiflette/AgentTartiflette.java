package AgentTartiflette;

import java.util.ArrayList;

import System.Interface;

public class AgentTartiflette {

	private int posX;
	private int posY;
	private Effecteur effecteur;
	private Capteur capteur;
	private Performance performance;
	private ArrayList<Fait> tabFait;

	public Performance getPerformance() {
		return performance;
	}

	public void movePosX(int n) {
		this.posX += n;
	}

	public void movePosY(int n) {
		this.posY += n;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Capteur getCapteur() {
		return capteur;
	}

	public Effecteur getEffecteur() {
		return effecteur;
	}

	public void setEffecteur(Effecteur effecteur) {
		this.effecteur = effecteur;
	}

	public ArrayList<Fait> getTabFait() {
		return tabFait;
	}

	public void setTabFait(ArrayList<Fait> tabFait) {
		this.tabFait = tabFait;
	}

	public AgentTartiflette() {
		this.posX = 0;
		this.posY = 0;
		this.capteur = new Capteur();
		this.effecteur = new Effecteur();
		this.performance = new Performance();
		this.tabFait = new ArrayList<Fait>();
	}

	/**
	 * L'agent parcours la carte en fonction de ses regles jusqu'à sa mort ou la
	 * sortie
	 * 
	 * @return Vrai l'agent meurt et Faux si il est sorti
	 */
	public boolean Boucle() {
		boolean estSorti = false;
		boolean estMort = false;
		// L'agent est sur une case meurtriere
		estMort = Interface.environnement.estDangereux(this);
		System.out.println("estMort = " + estMort);
		// Tant qu'il n'est pas moort ou sorti du niveau
		while (!estMort && !estSorti) {
			System.out.println("----------------------------------------");
			// Regarder environnement avec capteur
			capteur.capterCaseTartiflette(Interface.environnement, posX, posY);
			System.out.println("Fait = " + capteur.getCaracteristiqueCase());
			// Ajouter une fait
			Fait fait = new Fait(posX, posY, capteur.getCaracteristiqueCase());
			if (!tabFait.contains(fait))
				tabFait.add(fait);
			// Demander regle pour ajouter une action

			// Executer l'action suivante
			estSorti = effecteur.ExecuterActionSuivante(this);
			System.out.println("estSorti = " + estSorti);
			// L'agent est sur une case meurtriere
			estMort = Interface.environnement.estDangereux(this);
			System.out.println("estMort = " + estMort);

			System.out.println(this.toString());

			// Pause de 3 secondes
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (estMort && estSorti)
			System.err.println("Erreur : Il est a la fois sorti et mort.");
		if (estMort)
			return true;
		// Cela veut dire que c'est estSorti qui est true
		else
			return false;
	}

	public void suppressionFait() {
		this.tabFait = new ArrayList<Fait>();
	}

	@Override
	public String toString() {
		return "AgentTartiflette = [\nposX = " + posX + ", posY = " + posY + ", performance = " + performance
				+ ", \ncapteur=" + capteur + ", \neffecteur = " + effecteur + ", \ntabFait = " + tabFait.toString()
				+ "]";
	}

}