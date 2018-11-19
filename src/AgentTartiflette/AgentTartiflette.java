package AgentTartiflette;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import System.Interface;

public class AgentTartiflette {

	private int posX;
	private int posY;
	private String dernierDeplacement;
	private Effecteur effecteur;
	private Capteur capteur;
	private Performance performance;
	private ArrayList<Fait> tabFait;

	public AgentTartiflette() {
		this.posX = 0;
		this.posY = 0;
		this.capteur = new Capteur();
		this.effecteur = new Effecteur();
		this.performance = new Performance();
		this.tabFait = new ArrayList<Fait>();
		this.dernierDeplacement = "";
	}

	/**
	 * Boucle principale de l'agent L'agent parcours la carte en fonction de ses
	 * regles jusqu'à sa mort ou la sortie
	 * 
	 * @return Vrai l'agent meurt et Faux si il est sorti
	 * @throws IOException
	 */

	public boolean Boucle() throws IOException {
		boolean estSorti = false;
		boolean estMort = false;
		
		// L'agent est sur une case meurtriere
		estMort = Interface.environnement.estDangereux(this);
		System.out.println("estMort = " + estMort);
		System.out.println("----------------------------------------");
		// L'agent regarde sa case avec son capteur
		capteur.capterCaseTartiflette(Interface.environnement, posX, posY);
		System.out.println("Fait = " + capteur.getCaracteristiqueCase());
		// Il ajoute fait de sa case dans sa memoire, s'il n'est pas deja present
		Fait fait = new Fait(posX, posY, capteur.getCaracteristiqueCase());
		if (!tabFait.contains(fait))
			tabFait.add(fait);
		// puis les reecrire tous dans le fichier texte
		ecrireFichierTexte("faits.txt", tabFait);
		// Si l'effecteur n'a plus d'actions a effectuer, alors il fait une generation
		// de fait
		

		// Tant qu'il n'est pas mort ou sorti du niveau
		while (!estMort && !estSorti) {
			
			if (effecteur.getActions().isEmpty()) {
				// Demander regle dans le fichier texte
				Runtime runtime = Runtime.getRuntime();
				try {
					// Run du script python
					runtime.exec("py.exe moteur.py");
					ArrayList<String> actionsGegenereePython = lireFichierTexte("action.txt");
					// Si il n'y a pas d'action generee, alors generation aleatoire d'une action
					if (!actionsGegenereePython.isEmpty()) {
						// ajout de la liste d'action generee aux actions deja presentes
						effecteur.setActions(actionsGegenereePython);
					} else {
						String actionAleatoire = genererActionAletoire();
						effecteur.addAction(actionAleatoire);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			// Executer l'action suivante presente dans l'effecteur
			estSorti=effecteur.ExecuterActionSuivante(this);
//			if(Interface.environnement.getCase(posX, posY).getPortailMagique())
//				estSorti=true;
			System.out.println("estSorti = " + estSorti);
			// L'agent est sur une case meurtriere
			estMort = Interface.environnement.estDangereux(this);
			System.out.println("estMort = " + estMort);
			if (estMort) {
				capteur.capterCaseTartiflette(Interface.environnement, posX, posY);
				tabFait.add(new Fait(posX, posY, capteur.getCaracteristiqueCase()));
			}
			
			System.out.println("----------------------------------------");
			// L'agent regarde sa case avec son capteur
			capteur.capterCaseTartiflette(Interface.environnement, posX, posY);
			System.out.println("Fait = " + capteur.getCaracteristiqueCase());
			// Il ajoute fait de sa case dans sa memoire, s'il n'est pas deja present
			fait = new Fait(posX, posY, capteur.getCaracteristiqueCase());
			if (!tabFait.contains(fait))
				tabFait.add(fait);
			// puis les reecrire tous dans le fichier texte
			ecrireFichierTexte("faits.txt", tabFait);
			// Si l'effecteur n'a plus d'actions a effectuer, alors il fait une generation
			// de fait
			// Affichage de l'agent
			System.out.println(this.toString());
			// Pause de 2 secondes
			try {
				Thread.sleep(2000);
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

	/**
	 * Genere une action aleatoire
	 * 
	 * @return Si erreur "Erreur de la generation de l'action aleatoire" Probabilite
	 *         Probabilite de 2/9 de retourner "Gauche", "Droite", "Haut", "Bas"
	 *         Probabilite de 1/9 de retourner "Tirer double_aleatoire
	 *         double_aleatoire"
	 */
	private String genererActionAletoire() {
		System.out.println("Choix d'une action au hasard");
		String randomAction = "Erreur de la generation de l'action aleatoire";
		Double n = new Double(1);
		do {
			n = Math.random();
		} while (!(n < (double) 1) || deplacementEstDansLesFaits(n));
		if (n < (double) 2 / 9 && inclusCarteHorizontale(-1)) {
			randomAction = "Gauche";
		} else if (n < (double) 4 / 9 && inclusCarteHorizontale(1)) {
			randomAction = "Droite";
		} else if (n < (double) 6 / 9 && inclusCarteVerticale(-1)) {
			randomAction = "Haut";
		} else if (n < (double) 8 / 9 && inclusCarteVerticale(1)) {
			randomAction = "Bas";
		}
		else if (n < (double) 9 / 9) {
			boolean trouver=false;
			for(int i=0;i<tabFait.size()&&!trouver;i++)
				if(tabFait.get(i).getCaracteristique()=="Monstre")
					trouver=true;
			if(trouver)
			{
				randomAction = "Tirer";
				int p;
				// Probabilite de 0,5 de tirer horizontalement
				if (Math.random() < 0.5) {
					do {
						p = (Math.random() < 0.5 ? 1 : -1);
					} while (!inclusCarteHorizontale(p));
					randomAction += " " + new Integer((int) (posX + p)).toString();
					randomAction += " " + posY;
				} else {// Probabilite de 0,5 de tirer verticalament
					do {
						p = (Math.random() < 0.5 ? 1 : -1);
					} while (!inclusCarteVerticale(p));
					randomAction += " " + posX;
					randomAction += " " + new Integer((int) (posY + p)).toString();
				}
			}
		}
		// Si la generation n'a pas correctement fonctionnee, affichage d'une erreur
		if (randomAction.equals("Erreur de la generation de l'action aleatoire"))
			System.err.println(randomAction);
		return randomAction;
	}

	/**
	 * Pour eviter de rester coince, l'agent a un probabilite de 0.1 d'aller sur la
	 * case et ce quelque soit ses faits
	 * 
	 * @param n
	 * @return Vrai si l'agent se retrouvera sur une case deja exploree
	 */
	private boolean deplacementEstDansLesFaits(Double n) {
		int m = 0;
		boolean resultat = false;
		if (Math.random() < 0.9) {
			if (n < (double) 1 / 4) {
				m = -1;
				resultat = alreadyExplored(posX + m, posY);
			} else if (n < (double) 2 / 4) {
				m = 1;
				resultat = alreadyExplored(posX + m, posY);
			} else if (n < (double) 3 / 4) {
				m = -1;
				resultat = alreadyExplored(posX, posY + m);
			} else if (n < (double) 4 / 4) {
				m = 1;
				resultat = alreadyExplored(posX, posY + m);
			}
		} else
			resultat = false;
		return resultat;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return Vrai si la case x, y est deja presente dans les faits de l'agent
	 */
	private boolean alreadyExplored(int x, int y) {
		boolean resultat = false;
		Fait fait = null;
		int i = 0;
		if (!tabFait.isEmpty()) {
			while ((i < tabFait.size() && !resultat)) {
				fait = tabFait.get(i);
				if(fait.getCaracteristique()!="Vide")
					if (fait.hasSamePosition(x, y))
						resultat = true;
				i++;
			}
		}
		return resultat;
	}

	/**
	 * Lis les actions dans le fichier texte pour les ajouter dans le tableau de
	 * retour
	 * 
	 * @param chemin
	 *            du fichier texte
	 * @return un tableau contenant les actions à effectuer
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> lireFichierTexte(String chemin) throws FileNotFoundException {
		ArrayList<String> resultat = new ArrayList<String>();
		Scanner scanner = new Scanner(new File(chemin));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			// System.out.println(line);
			ArrayList<String> tab = normaliserAction(line);
			resultat.addAll(tab);
			// effecteur.addAction(line);
		}
		scanner.close();
		return resultat;
	}

	
	/**
	 * Permet de gerer les 'Go'
	 * @param line
	 * @return
	 */
	private ArrayList<String> normaliserAction(String line) {
		ArrayList<String> resultat = new ArrayList<String>();
		String[] lineArray = line.split(" ");
		System.out.println(line);
		if(lineArray.length>0)
			if (lineArray[0].equals("Go")) {		
				Integer deplacementX = new Integer(lineArray[1]) - posX;
				if (deplacementX > 0) {
					for (int i = 0; i < deplacementX; i++) {
						resultat.add("Droite");
					}
				} else {
					for (int i = deplacementX; i > 0; i--) {
						resultat.add("Gauche");
					}
				}
				
				Integer deplacementY = new Integer(lineArray[1]) - posY;
				if (deplacementY > 0) {
					for (int i = 0; i < deplacementY; i++) {
						resultat.add("Bas");
					}
				} else {
					for (int i = deplacementY; i > 0; i--) {
						resultat.add("Haut");
					}
				}
		}
		else {
			resultat.add(line);
		}
		return resultat;
	}

	public void ecrireFichierTexte(String chemin, ArrayList<Fait> tabFait) throws IOException {
		File file = new File(chemin);
		file.createNewFile();
		FileWriter fileWritrer = new FileWriter(file);
		fileWritrer.write(dernierDeplacement);
		for (Fait fait : tabFait) {
			if (!fait.getCaracteristique().equals("Vide"))// Les cases vides ne sont aps enregistrees dans le fichier
															// texte
				fileWritrer.write(fait.toStringFile() + "\n"); // ecrire une ligne dans le fichier resultat.txt
		}
		fileWritrer.close(); // fermer le fichier a la fin des traitements
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void disparitionMonstreDesFaits(int x, int y) {
		Fait fait = new Fait(x, y, "monstre");
		tabFait.remove(fait);
	}

	/**
	 * L'agent se deplace de n sur l'axe des abscisses
	 * 
	 * @param n
	 */
	public void movePosX(int n) {
		if (inclusCarteHorizontale(n)) {
			this.posX += n;
			performance.add(-1);// Performance -1
			String s = capteur.getCaracteristiqueCase();
			if (s.equals("Vide")) {
				dernierDeplacement = "";
			} else {
				dernierDeplacement = n < 0 ? "G" : "D";
				dernierDeplacement += s + "\n";
			}
		} else
			System.err.println("L'agent a tente de sortir de la carte sur l'axe des abscisses !");
	}

	/**
	 * L'agent se deplace de n sur l'axe des ordonnees
	 * 
	 * @param n
	 */
	public void movePosY(int n) {
		if (inclusCarteVerticale(n)) {
			this.posY += n;
			performance.add(-1);// Performance -1
			String s = capteur.getCaracteristiqueCase();
			if (s.equals("Vide")) {
				dernierDeplacement = "";
			} else {
				dernierDeplacement = n < 0 ? "H" : "B";
				dernierDeplacement += s + "\n";
			}
		} else
			System.err.println("L'agent a tente de sortir de la carte sur l'axe des ordonnes !");
	}

	/**
	 * 
	 * @param n
	 * @return Vrai si l'agent desire acceder à une case de la carte qui existe sur
	 *         l'axe des abscisses.
	 */
	public boolean inclusCarteHorizontale(int n) {
		return -1 < this.posX + n && this.posX + n < Interface.environnement.getTaille();
	}

	/**
	 * 
	 * @param n
	 * @return Vrai si l'agent desire acceder à une case de la carte qui existe sur
	 *         l'axe des ordonnees.
	 */
	public boolean inclusCarteVerticale(int n) {
		return -1 < this.posY + n && this.posY + n < Interface.environnement.getTaille();
	}

	/**
	 * Supprime les faits enregistrees dans l'agent
	 */
	public void suppressionFait() {
		this.tabFait = new ArrayList<Fait>();
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

	public Performance getPerformance() {
		return performance;
	}

	@Override
	public String toString() {
		return "AgentTartiflette [posX = " + posX + ", posY = " + posY + ", dernierDeplacement = " + dernierDeplacement
				+ ", performance=" + performance + ", \neffecteur=" + effecteur + ", \ncapteur=" + capteur
				+ ", \ntabFait=" + tabFait + "]";
	}
}