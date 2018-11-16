package AgentTartiflette;

import System.Interface;

public class AgentTartiflette {



	private int posX=0;
	private int posY=0;
	private Effecteur effecteur;
	private Capteur capteur;
	private Performance performance;


	public Performance getPerformance()
	{
		return performance;
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

	public AgentTartiflette() {
		this.capteur = new Capteur();
		this.effecteur = new Effecteur();
		this.performance = new Performance();
	}

	public boolean Boucle()
	}
			return false;
		else 
		//Cela veut dire que c'est estSorti qui est true
			return true;
		if(estMort)
		}
			
			//Executer l'action
			//AjouterFait et demander regle
			//Regarder environnement avec Capteur
		{
		while(!estMort&&!estSorti)
		boolean estMort=false;
		boolean estSorti=false;
	{
	public boolean ExecuterActionSuivante() {
		boolean estSorti = false;
		String[] action = effecteur.actionSuivante().split(" ");
		if (action[0].equals("Gauche")) {
			y--;// D�placement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Droite")) {
			y++;// D�placement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Haut")) {
			x--;// D�placement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Bas")) {
			x++;// D�placement
			performance.add(-1);// Performance -1
			capteur.capterCaseTartiflette(Interface.environnement, x, y);
		} else if (action[0].equals("Tirer")) {
			performance.add(-10);// Performance -10
			// Tuer le monstre qui est �ventuellement dans une case adjacente en (x, y+1),
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

					"L'action suivante n'est pas �gale � 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer', ou � 'Sortir'.");
		return estSorti;
	}
}