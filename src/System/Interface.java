package System;

import java.awt.Dimension;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javax.swing.JComponent;

import AgentTartiflette.AgentTartiflette;
import AgentTartiflette.Effecteur;
import Environnement.Environnement;

public class Interface extends JComponent {

	public static Environnement environnement;

	public static AgentTartiflette agentTartiflette;

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		boolean cacaLicorne = false;

		boolean vent = false;
		boolean crevasse = false;
		boolean monstreGayPride = false;
		boolean agentTartifletteIci = false;
		boolean portailMagique = false;
		String path;

		// Parcours de toutes les cases
		for (int i = 0; i < Interface.environnement.getTaille(); i++) {
			for (int j = 0; j < Interface.environnement.getTaille(); j++) {
				path = ".\\src\\ressources\\";
				portailMagique = Interface.environnement.getCase(i, j).getPortailMagique();
				cacaLicorne = Interface.environnement.getCase(i, j).getCacaLicorne();
				vent = Interface.environnement.getCase(i, j).getVent();
				crevasse = Interface.environnement.getCase(i, j).getCrevasse();
				monstreGayPride = Interface.environnement.getCase(i, j).getMonstreGayPride();
				agentTartifletteIci = Interface.agentTartiflette.getPosX() == i
						&& Interface.agentTartiflette.getPosY() == j;

				if (!agentTartifletteIci) {

					if (cacaLicorne)

						path = path + "cacaLicorne.jpg";

					else if (vent)

						path = path + "vent.jpg";

					else if (crevasse)

						path = path + "crevasse.jpg";

					else if (monstreGayPride)

						path = path + "monstreGayPride.jpg";

					else if (portailMagique)

						path = path + "portailMagique.jpg";

					// vide

					else

						path = path + "case_vide.png";

				} else {

					if (cacaLicorne)

						path = path + "caca+agent.jpg";

					else if (vent)

						path = path + "vent+agent.jpg";

					else if (crevasse)

						path = path + "trou+agent.jpg";

					else if (monstreGayPride)

						path = path + "monstre+agent.jpg";

					else if (portailMagique)

						path = path + "portailMagique+agent.jpg";

					// vide

					else

						path = path + "agent.jpg";

				}

				BufferedImage img = null;

				// Lecture du fichier image

				try {

					img = ImageIO.read(new File(path));

				} catch (IOException ex) {

					Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);

				}

				// Ecriture de l'image sur le canvas

				g.drawImage(img, 50 * i, 50 * j, null);

			}

		}

	}

	public Dimension getPreferredSize() {

		return new Dimension(50 * Interface.environnement.getTaille(), 50 * Interface.environnement.getTaille());

	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public static void main(String args[]) {

		int nombreCase = 10;
		boolean estMort = false;
		Interface.agentTartiflette = new AgentTartiflette();
		Effecteur effecteur = new Effecteur();
		for (int i = 0; i < nombreCase; i++) {
			effecteur.addAction("Droite");
		}
		Interface.agentTartiflette.setEffecteur(effecteur);
		Interface.environnement = new Environnement(nombreCase);

		ThreadAffichage threadAffichage = new ThreadAffichage();
		threadAffichage.start();

		while (true) {
			estMort = Interface.agentTartiflette.Boucle();
			
			// La position de l'agent est reinitialise à (0, 0)
			Interface.agentTartiflette.setPosX(0);
			Interface.agentTartiflette.setPosY(0);
			
			if (estMort) {
				Interface.agentTartiflette.getPerformance().add(-10 * (nombreCase) ^ 2);
				estMort = false;
			}
			// Cela veut dire qu'il est sorti
			else {
				nombreCase++;
				Interface.environnement = new Environnement(nombreCase);
				// Les faits/croyances sont supprimes
				Interface.agentTartiflette.suppressionFait();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}