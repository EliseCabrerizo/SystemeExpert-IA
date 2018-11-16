package AgentTartiflette;

import java.util.ArrayList;
import Environnement.Environnement;
import System.Interface;

public class Effecteur {

	private ArrayList<String> Actions;

	public Effecteur() {
		Actions = new ArrayList<>();
	}

	public void addAction(String action) {
		if (action.equals("Gauche") || action.equals("Droite") || action.equals("Haut") || action.equals("Bas")
				|| action.split(" ")[0].equals("Tirer") || action.equals("Sortir"))
			Actions.add(action);
		else
			System.err.println(
					"L'action n'est pas egale a 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer abscisse ordonnee', ou a 'Sortir'.");
	}

	/**
	 * 
	 * @return "NUL" si la liste des actions est vide, sinon l'action
	 */
	public String actionSuivante() {
		if (Actions.size() != 0) {
			String resultat = Actions.get(0);
			Actions.remove(0);
			return resultat;
		}
		return "NUL";
	}

	/**
	 * Fonction qui execute la prochaine action pr�sente dans Actions
	 * 
	 * @param agentTartiflette
	 * @return Vrai ou faux si l'agent a pris le portail ou non
	 */
	public boolean ExecuterActionSuivante(AgentTartiflette agentTartiflette) {
		boolean estSorti = false;
		String[] action = actionSuivante().split(" ");
		if (action[0].equals("NUL")) {
			// Pas d'actions
			System.out.println("Pas d'action");
		} else if (action[0].equals("Gauche")) {
			System.out.println(action[0]);
			agentTartiflette.movePosX(-1);// Deplacement
			agentTartiflette.getPerformance().add(-1);// Performance -1
		} else if (action[0].equals("Droite")) {
			System.out.println(action[0]);
			agentTartiflette.movePosX(1);// Deplacement
			agentTartiflette.getPerformance().add(-1);// Performance -1
		} else if (action[0].equals("Haut")) {
			System.out.println(action[0]);
			agentTartiflette.movePosY(-1);// Deplacement
			agentTartiflette.getPerformance().add(-1);// Performance -1
		} else if (action[0].equals("Bas")) {
			System.out.println(action[0]);
			agentTartiflette.movePosY(1);// Deplacement
			agentTartiflette.getPerformance().add(-1);// Performance -1
		} else if (action[0].equals("Tirer")) {
			System.out.println(action);
			agentTartiflette.getPerformance().add(-10); // Performance -10
			// Tuer le monstre qui est eventuellement dans une case adjacente en (posX,
			// posX+1), (posX, posX-1), (posX+1, posX), (posX-1, posX)
			Interface.environnement.disparitionMontre(new Integer(action[1]), new Integer(action[2]));
//			agentTartiflette.disparitionMonstreDesFaits(new Integer(action[1]), new Integer(action[2]));
		} else if (action[0].equals("Sortir")) {
			System.out.println(action[0]);
			// Augementer la performance
			agentTartiflette.getPerformance().add(10 * (Interface.environnement.getTaille()) ^ 2);
			// L'agent sortira
			estSorti = true;
			// L'agent est repositionne en 0, 0
			agentTartiflette.setPosX(0);
			agentTartiflette.setPosY(0);
		} else
			System.err.println(
					"L'action suivante n'est pas egale a 'NUL', 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer', ou a 'Sortir'.");
		return estSorti;
	}

	public ArrayList<String> getActions() {
		return Actions;
	}

	public void setActions(ArrayList<String> actions) {
		Actions = actions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Actions == null) ? 0 : Actions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Effecteur))
			return false;
		Effecteur other = (Effecteur) obj;
		if (Actions == null) {
			if (other.Actions != null)
				return false;
		} else if (!Actions.equals(other.Actions))
			return false;
		return true;
	}

	public void tuerMonstre(int i, int j, Environnement environnement) {
		environnement.disparitionMontre(i, j);
	}

	@Override
	public String toString() {
		return "Effecteur [Actions=" + Actions + "]";
	}

}