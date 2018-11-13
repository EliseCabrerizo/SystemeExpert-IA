package Environnement;

public class Environnement {



	private Case[][] Case;
	private int taille;

	public int getTaille() {
		return this.taille;
	}

	public Case getCase(int x, int y) {
		if (x >= 0 && x < this.getTaille() && y >= 0 && y < this.getTaille())
			return this.Case[x][y];
		else 
			return null;
	}

	public void setCase(int x, int y, boolean cacaLicorne, boolean montreGayPride,boolean vent, boolean crevasse) {
		if (x > 0 && x < this.getTaille() && y > 0 && y < this.getTaille()) {
			this.Case[x][y].setCacaLicorne(cacaLicorne);
			this.Case[x][y].setMonstreGayPride(montreGayPride);
			this.Case[x][y].setCrevasse(crevasse);
			this.Case[x][y].setVent(vent);
		} else 
			throw new UnsupportedOperationException();
	}


	public Environnement(int nbCase) {

		if (nbCase > 0) {
			this.taille = nbCase;
			this.Case = new Case[nbCase][nbCase];
			for(int i=0;i<nbCase;i++)
				for(int j=0;j<nbCase;j++)
					Case[i][j]= new Case(false,false,false,false);
			Generation();
		}
	}
	
	public boolean VerificationCase(int x, int y)
	{
		boolean aRetourner=false;
		if(x==0)
		{
			if(y==0)
			{
				if(Case[x+1][y].Vide()&&Case[x][y+1].Vide())
					aRetourner=true;
			}
			else if(y==taille-1)
			{
				if(Case[x+1][y].Vide()&&Case[x][y-1].Vide())
					aRetourner=true;
			}
			else 
			{
				if(Case[x+1][y].Vide()&&Case[x][y-1].Vide()&&Case[x][y+1].Vide())
					aRetourner=true;
			}
		}
		else if(x==taille-1)
		{
			if(y==0)
			{
				if(Case[x-1][y].Vide()&&Case[x][y+1].Vide())
					aRetourner=true;
			}
			else if(y==taille-1)
			{
				if(Case[x-1][y].Vide()&&Case[x][y-1].Vide())
					aRetourner=true;
			}
			else 
			{
				if(Case[x-1][y].Vide()&&Case[x][y-1].Vide()&&Case[x][y+1].Vide())
					aRetourner=true;
			}
		}
		else 
		{
			if(y==taille-1)
			{
				if(Case[x+1][y].Vide()&&Case[x-1][y].Vide()&&Case[x][y-1].Vide())
					aRetourner=true;
			}
			else if(y==0)
			{
				if(Case[x+1][y].Vide()&&Case[x-1][y].Vide()&&Case[x][y+1].Vide())
					aRetourner=true;
			}
			else if (Case[x+1][y].Vide()&&Case[x-1][y].Vide()&&Case[x][y-1].Vide()&&Case[x][y+1].Vide())
						aRetourner=true;
			
		}
		return aRetourner;
	}
	
	public void PositionnerElement(int element, int x, int y)
	{
		if(element==1)
		{
			Case[x][y].setMonstreGayPride(true);
			if(x==0)
			{
				if(y==0)
				{
					Case[x+1][y].setCacaLicorne(true);
					Case[x][y+1].setCacaLicorne(true);
				}
				else if(y==taille-1)
				{
					Case[x+1][y].setCacaLicorne(true);
					Case[x][y-1].setCacaLicorne(true);
				}
				else 
				{
					Case[x+1][y].setCacaLicorne(true);
					Case[x][y-1].setCacaLicorne(true);
					Case[x][y+1].setCacaLicorne(true);
				}
			}
			else if(x==taille-1)
			{
				if(y==0)
				{
					Case[x-1][y].setCacaLicorne(true);
					Case[x][y+1].setCacaLicorne(true);
				}
				else if(y==taille-1)
				{
					Case[x-1][y].setCacaLicorne(true);
					Case[x][y-1].setCacaLicorne(true);
				}
				else 
				{
					Case[x-1][y].setCacaLicorne(true);
					Case[x][y-1].setCacaLicorne(true);
					Case[x][y+1].setCacaLicorne(true);
				}
			}
			else 
			{
				if(y==taille-1)
				{
					Case[x+1][y].setCacaLicorne(true);
					Case[x-1][y].setCacaLicorne(true);
					Case[x][y-1].setCacaLicorne(true);
				}
				else if(y==0)
				{
					Case[x+1][y].setCacaLicorne(true);
					Case[x-1][y].setCacaLicorne(true);
					Case[x][y+1].setCacaLicorne(true);
				}
				else 
				{
					Case[x+1][y].setCacaLicorne(true);
					Case[x-1][y].setCacaLicorne(true);
					Case[x][y-1].setCacaLicorne(true);
					Case[x][y+1].setCacaLicorne(true);
				}
				
			}
		}
		else if(element==2)
		{
			Case[x][y].setCrevasse(true);
			if(x==0)
			{
				if(y==0)
				{
					Case[x+1][y].setVent(true);
					Case[x][y+1].setVent(true);
				}
				else if(y==taille-1)
				{
					Case[x+1][y].setVent(true);
					Case[x][y-1].setVent(true);
				}
				else 
				{
					Case[x+1][y].setVent(true);
					Case[x][y-1].setVent(true);
					Case[x][y+1].setVent(true);
				}
			}
			else if(x==taille-1)
			{
				if(y==0)
				{
					Case[x-1][y].setVent(true);
					Case[x][y+1].setVent(true);
				}
				else if(y==taille-1)
				{
					Case[x-1][y].setVent(true);
					Case[x][y-1].setVent(true);
				}
				else 
				{
					Case[x-1][y].setVent(true);
					Case[x][y-1].setVent(true);
					Case[x][y+1].setVent(true);
				}
			}
			else 
			{
				if(y==taille-1)
				{
					Case[x+1][y].setVent(true);
					Case[x-1][y].setVent(true);
					Case[x][y-1].setVent(true);
				}
				else if(y==0)
				{
					Case[x+1][y].setVent(true);
					Case[x-1][y].setVent(true);
					Case[x][y+1].setVent(true);
				}
				else 
				{
					Case[x+1][y].setVent(true);
					Case[x-1][y].setVent(true);
					Case[x][y-1].setVent(true);
					Case[x][y+1].setVent(true);
				}
				
				
			}
		}
		
	}
	public void Generation() 
	{

		int randomX = (int) ((Math.random() * (this.getTaille())));
		int randomY = (int) ((Math.random() * (this.getTaille())));
		
		Case[randomX][randomY].setPortailMagique(true);
		
		int nombreTourBoucle = (int) ((Math.random() * (this.getTaille())));
		
		while(nombreTourBoucle>0)
		{
			randomX = (int) ((Math.random() * (this.getTaille())));
			randomY = (int) ((Math.random() * (this.getTaille())));
			
			if(VerificationCase(randomX,randomY))
			{
				int randomN = (int) ((Math.random() * (10)));
				if(randomN%2==0)
					PositionnerElement(1,randomX,randomY);
				else PositionnerElement(2,randomX,randomY);
			}
			nombreTourBoucle--;
		}
			
	}
	
}
	