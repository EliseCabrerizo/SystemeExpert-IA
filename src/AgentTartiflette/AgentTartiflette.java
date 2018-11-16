package AgentTartiflette;

import System.Interface;

public class AgentTartiflette {

	private int x; // Position des abscisses
	private int y; // Position des ordonnées
	private Effecteur effecteur;
	private Capteur capteur;
	private Performance performance;

	public AgentTartiflette() {
		this.x = 0;
		this.y = 0;
		this.capteur = new Capteur();
		this.effecteur = new Effecteur();
		this.performance = new Performance();
	}

	public boolean ExecuterActionSuivante() {
		boolean estSorti = false;
		String[] action = effecteur.actionSuivante().split(" ");
		if (action[0].equals("Gauche")) {
			y--;// Déplacement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Droite")) {
			y++;// Déplacement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Haut")) {
			x--;// Déplacement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Bas")) {
			x++;// Déplacement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Tirer")) {
			performance.add(-10);// Performance -10
			// Tuer le monstre qui est éventuellement dans une case adjacente en (x, y+1),
			// (x, y-1), (x+1, y), (x-1, y)
			Interface.environnement.disparitionMontre(new Integer(action[1]), new Integer(action[2]));
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Sortir")) {
			// Augementer la performance
			performance.add(10 * (Interface.environnement.getTaille()) ^ 2);
			// L'agent sortira
			estSorti = true;
			// L'agent est repositionner en 0, 0
			x = 0;
			y = 0;
		} else
			System.err.println(
					"L'action suivante n'est pas égale à 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer', ou à 'Sortir'.");
		return estSorti;
	}

}
