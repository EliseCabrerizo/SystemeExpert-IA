import os
import argparse
from regle import *


    

def main():
    Rfichier=open("regles.txt","r")
    listeR=Rfichier.read()
    Rfichier.close()
    AgentTartiflette=Agent()
    RegleAnd=Regle()
    RegleOr=RegleOR()
    Actionp=Action()
    # ici on va découper chaque ligne en 3 parties : le type de règle, ses faits requis et les actions possibles
    for l in listeR.split("\n"):
        t=""
        fr=[]
        mvt=[]
        RegleAnd=Regle()
        RegleOr=RegleOR()
        Actionp=Action()
        ReglePos=ReglePOS()
        RegleGto=RegleGTO()
        for p in l.split(":"):
            if(p == "AND" or p == "OR" or p == "POS" or p == "GTO"):
                t=p
            if "," in p :
                for f in p.split(","):
                    fr.append(f)
            if " " in p:
                for a in p.split(" "):
                    mvt.append(a)
        Actionp.updatemvt(mvt)
        if t == "AND":
            RegleAnd.update_faits_r(fr)
            RegleAnd.initAction(Actionp)
            AgentTartiflette.addR(RegleAnd)
        if t == "OR":
            RegleOr.update_faits_r(fr)
            RegleOr.initAction(Actionp)
            AgentTartiflette.addR(RegleOr)
        
        if t == "POS":
            ReglePos.update_faits_r(fr)
            ReglePos.initAction(Actionp)
            AgentTartiflette.addR(ReglePos)
        if t == "GTO":
            RegleGto.update_faits_r(fr)
            RegleGto.initAction(Actionp)
            AgentTartiflette.addR(RegleGto)

    #faits_c=["Hcaca","caca;2;2","vent;3;3","vent;3;5","caca;2;4","Porte;8;3","monstre;5;4"]
    Ffichier=open("faits.txt","r")
    faits_lu=Ffichier.read()
    Ffichier.close()
    faits_c=[]
    for f in faits_lu.split("\n"):
        faits_c.append(f)
    # print(" ")
    # print("=========================================")
    # AgentTartiflette.printTRules()
    # print("=========================================")
    # print(" ")
    # print(" ")
    AgentTartiflette.Verif(faits_c)
    AgentTartiflette.printP()
    print("=================   SELECTION D'ACTION   ================= ")
    AgentTartiflette.choisirAction()
                
            
if __name__ == '__main__':
    main()
