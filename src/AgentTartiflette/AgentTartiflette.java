package AgentTartiflette;

import System.Interface;

public class AgentTartiflette {

	private int posX;
	private int posY;
	private Effecteur effecteur;
	private Capteur capteur;
	private Performance performance;

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

	public AgentTartiflette() {
		this.posX = 0;
		this.posY = 0;
		this.capteur = new Capteur();
		this.effecteur = new Effecteur();
		this.performance = new Performance();
	}
/**
 * L'agent parcours la carte en fonction de ses regles
 * @return Vrai l'agent meurt et Faux si il est sorti
 */
	public boolean Boucle() {
		boolean estSorti = false;
		boolean estMort = false;
		// L'agent est sur une case meurtriere
		estMort = Interface.environnement.estDangereux(this);
		// Tant qu'il n'est pas moort ou sorti du niveau
		while (!estMort && !estSorti) {
			// Regarder environnement avec capteur
			capteur.capterCaseTartiflette(Interface.environnement, posX, posY);
			// AjouterFait et demander regle

			// Executer l'action
			estSorti = effecteur.ExecuterActionSuivante(this);
			// L'agent est sur une case meurtriere
			estMort = Interface.environnement.estDangereux(this);
		}
		if (estMort)
			return true;
		// Cela veut dire que c'est estSorti qui est true
		else
			return false;
	}

}