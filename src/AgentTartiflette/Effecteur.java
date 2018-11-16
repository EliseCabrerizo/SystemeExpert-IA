package AgentTartiflette;



import java.util.ArrayList;

import Environnement.Environnement;



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
			System.err.println("L'action n'est pas �gale � 'Gauche', 'Droite', 'Haut', 'Bas', 'Tirer abscisse ordonn�e', ou � 'Sortir'.");
	}



	public String actionSuivante() {

		String resultat = Actions.get(0);

		Actions.remove(0);

		return resultat;

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



}