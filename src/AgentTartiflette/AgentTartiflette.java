package AgentTartiflette;

import Environnement.Environnement;

public class AgentTartiflette {

	private int x; // Position des abscisses
	private int y; // Position des ordonn�es
	private Effecteur effecteur;
	private Capteur capteur;
	private Environnement environnement;
//	private Performance performance;

	public AgentTartiflette() {
		this.x = 0;
		this.y = 0;
		this.capteur = new Capteur();
		this.effecteur = new Effecteur();
		// this.environnement = new Environnement(3);
//		this.performance = new Performance();
	}

	public void ExecuterActionSuivante() {
		String action = effecteur.actionSuivante();
		if (action.equals("Gauche")) {
			y--;//D�placement
//			performance.add(-1);//Performance -1
			capteur.capterCaseTartiflette(environnement,x, y);
		} else if (action.equals("Droite")) {
			y++;//D�placement
//			performance.add(-1);//Performance -1
			capteur.capterCaseTartiflette(environnement,x, y);
		} else if (action.equals("Haut")) {
			x--;//D�placement
//			performance.add(-1);//Performance -1
			capteur.capterCaseTartiflette(environnement,x, y);
		} else if (action.equals("Bas")) {
			x++;//D�placement
//			performance.add(-1);//Performance -1
			capteur.capterCaseTartiflette(environnement,x, y);
		} else if (action.equals("Tirer")) {
//			performance.add(-10);//Performance -10
			//Tuer le monstre qui est �ventuellement dans une case adjacente en (x, y+1), (x, y-1), (x+1, y), (x-1, y)
			capteur.capterCaseTartiflette(environnement,x, y);
		} else if (action.equals("Sortir")) {
			// Augementer la performance
			// performance.add(10*(environnement.getLongueur())^2);
			//Cr�er un nouvel environnement avec un c�t� plus large de 1
		} else
			System.err.println(
					"L'action suivante n'est pas �gale � 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer', ou � 'Sortir'.");
	}

}
