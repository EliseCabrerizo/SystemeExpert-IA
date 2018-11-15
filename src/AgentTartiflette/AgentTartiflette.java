package AgentTartiflette;

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
	{
		boolean estSorti=false;
		boolean estMort=false;
		while(!estMort&&!estSorti)
		{
			//Regarder environnement avec Capteur
			//AjouterFait et demander regle
			//Executer l'action
			
		}
		if(estMort)
			return true;
		//Cela veut dire que c'est estSorti qui est true
		else 
			return false;
	}
	public void ExecuterActionSuivante() {
		String action = effecteur.actionSuivante();

		if (action.equals("Gauche")) {

			setPosY(getPosY() - 1);//Déplacement

			performance.add(-1);//Performance -1

//			capteur.capterCaseTartiflette(Interfaceenvironnement,x, y);

		} else if (action.equals("Droite")) {

			setPosY(getPosY() + 1);//Déplacement

			performance.add(-1);//Performance -1

//			capteur.capterCaseTartiflette(environnement,x, y);

		} else if (action.equals("Haut")) {

			setPosX(getPosX() - 1);//Déplacement

			performance.add(-1);//Performance -1

//			capteur.capterCaseTartiflette(environnement,x, y);

		} else if (action.equals("Bas")) {

			setPosX(getPosX() + 1);//Déplacement

			performance.add(-1);//Performance -1

//			capteur.capterCaseTartiflette(environnement,x, y);

		} else if (action.equals("Tirer")) {

			performance.add(-10);//Performance -10

			//Tuer le monstre qui est éventuellement dans une case adjacente en (x, y+1), (x, y-1), (x+1, y), (x-1, y)

//			capteur.capterCaseTartiflette(environnement,x, y);

		} else if (action.equals("Sortir")) {

			// Augementer la performance

//			 performance.add(10*(environnement.getLongueur())^2);

			//Créer un nouvel environnement avec un côté plus large de 1

		} else

			System.err.println(

					"L'action suivante n'est pas égale à 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer', ou à 'Sortir'.");

	}
}