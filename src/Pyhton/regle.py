import itertools
import sys
import re
import random

#ActionP = ["H", "B","D","Gauche","C","S", "F","Go To"] # liste des actions possible :Haut Bas Droite Gauche Cailoux Sortir 


class Action:
    def __init__(self):
        self.mvt=list()

    def initMVT(self,mvts):
        for m in mvts:
            self.mvt.append(m)

    def addmvt(self,m):
        self.mvt.append(m)
		
    def delmvt(self,m):
        self.mvt.remove(m)

    def resetmvt(self):
        for m in self.mvt :
            self.delmvt(m)
        self.mvt=list()

    def getfirst(self):
        return self.mvt[0]

    def updatemvt(self,ml):
        self.resetmvt()
        for m in ml :
            self.addmvt(m)

    def getAll(self):
        ac=""
        for m in self.mvt:
            ac=ac+m+" \n"
        return ac

    def printA(self):
        for m in self.mvt:
            print(m)
			
class Regle:

    def __init__(self):
        self.action=Action() # Action pouvant être ajouté au plan si inférence ok
        self.faits_r=list() # liste des faits requis

    def initAction(self,A):
        self.action=A

    def update_faits_r(self,faits):
        # on supprime tous les anciens faits nécessaires
        for f in self.faits_r:
            self.faits_r.remove(f)
        # on ajoute les nouveaux
        for f in faits:
            self.faits_r.append(f)
			
    def inference(self,faits_c) : # inference de base où tous nos faits requis doivent être vrai (A ET B ET C ...)
        for fr in self.faits_r  :
            if fr not in faits_c: # si un de nos faits requis n'est pas dans les faits connus on renvoie faux
                return False
        return True

    def printRule(self):
        print("*********************************")
        print("Regle AND")
        print("fait requis")
        for fr in self.faits_r:
            print(fr)
        print("MVT de l'action possible :")
        self.action.printA()
        print("*********************************")

class RegleOR(Regle):

    def inference(self,faits_c):
         for fr in self.faits_r  :
                if fr in faits_c: # si un de nos faits requis n'est pas dans les faits connus on renvoie faux
                    return True
         return False

    def printRule(self):
        print("*********************************")
        print("Regle OR")
        print("fait requis")
        for fr in self.faits_r:
            print(fr)
        print("MVT de l'action possible :")
        self.action.printA()
        print("*********************************")

class ReglePOS(Regle):

    def __init__(self):
        self.action=Action() # Action pouvant être ajouté au plan si inférence ok
        self.faits_r=list() # liste des faits requis
        self.X=0
        self.Y=0

   
   
    def inference(self,faits_c):
        ##print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        #print("Dans inference Regle POS")
        for fc in faits_c:
            # si on match la première expression
            #print("FR = "+self.faits_r[0])
            #print("FC = "+fc)
            if re.match(self.faits_r[0], fc) is not None:
                #print("match ! FC  = "+fc+"FR = "+self.faits_r[0])
                s=fc.split(";")
                self.x=int(s[1])
                self.y=int(s[2])
                trouver=s[0]+";"+str(self.x)+";"+str(self.y+2)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X, Y+2
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x,Y+2) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = "+ac)
                        ac=ac+" "+str(self.x)+" "+str(self.y+1)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True

                trouver=s[0]+";"+str(self.x)+";"+str(self.y-2)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X, Y-2
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x,Y-2) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" "+str(self.x)+" "+str(self.y-1)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
                
                trouver=s[0]+";"+str(self.x+2)+";"+str(self.y)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X+2, Y
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x+2,Y) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" "+str(self.x+1)+" "+str(self.y)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
                
                trouver=s[0]+";"+str(self.x-2)+";"+str(self.y)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X-2, Y
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x-2,Y) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" "+str(self.x-1)+" "+str(self.y)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
                
                trouver=s[0]+";"+str(self.x+1)+";"+str(self.y+1)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X+1, Y+1
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x+1,Y+1) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" X+1,Y+1"
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
                
                trouver=s[0]+";"+str(self.x+1)+";"+str(self.y-1)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X+1, Y-1
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x+1,Y-1) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" "+str(self.x)+" "+str(self.y+1)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
                
                trouver=s[0]+";"+str(self.x-1)+";"+str(self.y+1)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X+1, Y+1
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x-1,Y+1) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" "+str(self.x)+" "+str(self.y+1)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
                
                trouver=s[0]+";"+str(self.x-1)+";"+str(self.y-1)
                # on reparcours tous les faits connu à la recherche d'un truc identique en pos X+1, Y-1
                for fcd in faits_c:
                    if fcd == trouver:
                        # on a trouver le même en (x-1,Y-1) donc il est au mileu
                        ac=""
                        ac=ac+self.action.getfirst()
                        #print("ac = ")
                        ac=ac+" "+str(self.x)+" "+str(self.y-1)
                        self.action.resetmvt()
                        self.action.addmvt(ac)
                        #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                        return True
        ##print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        return False        
    
    def printRule(self):
        
        print("*********************************")
        print("Regle POS")
        print("fait requis")
        for fr in self.faits_r:
            print(fr)
        print("X = "+str(self.X)+" Y = "+str(self.Y))
        print("MVT de l'action possible :")
        self.action.printA()
        print("*********************************")


class RegleGTO(Regle):
    def __init__(self):
        self.action=Action() # Action pouvant être ajouté au plan si inférence ok
        self.faits_r=list() # liste des faits requis
        self.X=0
        self.Y=0

    def inference(self,faits_c):
        ##print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        #print("Dans inference Regle POS")
        for fc in faits_c:
            # si on match la première expression
            #print("FR = "+self.faits_r[0])
            #print("FC = "+fc)
            if re.match(self.faits_r[0], fc) is not None:
                #Si on a matcher cela veut dire que la règle est respectée et qu'il faut aller à la case à gauche de celle-ci
                s=fc.split(";")
                self.x=int(s[1])
                self.y=int(s[2])
                aco=self.action.getAll()
                #print("ACO = "+aco)
                #print("ac = "+ac)
                if self.x > 0: # si possible à gauche
                    ac="Go "+str(self.x-1)+" "+str(self.y)
                    self.action.resetmvt()
                    self.action.addmvt(ac)
                    self.action.addmvt(aco)
                    #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                    return True
                else : # sinon on va sur la case à droite
                    if aco[0] == 'D':
                        aco="G "+aco[1:len(aco)]
                    ac="Go "+str(self.x+1)+" "+str(self.y)
                    self.action.resetmvt()
                    self.action.addmvt(ac)
                    self.action.addmvt(aco)
                    #print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                    return True



             
class Agent:
    
    def __init__(self):
        self.R=[]   # ensemble de règles possibles
        self.A=[]   # ensemble des actions possibles
    
    def addR(self,Regle):
        self.R.append(Regle)
    
    def delR(self,Regle):
        self.R.remove(Regle)

    def addA(self,Action):
        self.A.append(Action)
    
    def delA(self,Action):
        self.A.remove(Action)
    
    def dropP(self):    #reset la liste d'action possible
        for a in self.A:
            self.A.remove(a)
    
    def Verif(self,faits_c):
        self.dropP()
        for r in self.R :
            #print("inference r = ")
            #r.printRule()
            if r.inference(faits_c):
                #print("Innference reussi")
                #print("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*")
                #r.printRule()
                self.A.append(r.action)
    
    def choisirAction(self):
        Ffichier=open("faits.txt","a")
        Afichier=open("action.txt","w")
        if len(self.A) == 0:
            print("RAAAAAANDOM !!!")
            actionRandom = random.randint(1,4)
            if actionRandom == 1:
                print("Gauche")
                Afichier.write("Gauche")
                Afichier.close()
                Ffichier.close()
            if actionRandom == 2:
                print("Droite")
                Afichier.write("Droite")
                Afichier.close()
                Ffichier.close()
            if actionRandom == 3:
                print("Haut")
                Afichier.write("Haut")
                Afichier.close()
                Ffichier.close()
            if actionRandom == 4:
                print("Bas")
                Afichier.write("Bas")
                Afichier.close()
                Ffichier.close()
        
        for action in self.A: #    ajouter à la base de fait qu'on a trouver une crevasse
            for m in action.mvt:
                    if "N" in m :
                        print("CREVASSE !!")
                        action.printA()
                        s=m.split(" ")
                        nf="Crevasse;"+s[1]+";"+s[2]+"\n"
                        print("nf = "+nf)
                        Ffichier.write(nf)

        for action in self.A:
             for m in action.mvt:
                    if "Sortie" in m :
                        print("JE VAIS SORTIR")
                        action.printA()
                        for mm in action.mvt:
                            an=mm+"\n"
                            Afichier.write(an)
                        Afichier.close()
                        Ffichier.close()
                        return True
        # on a pas d'action qui nous permet de sortir dessuite => on va essayer de taper
        for action in self.A:
            for m in action.mvt:
                if "Tirer" in m :
                    print("LANCEZ LE REBLOCHON !!")
                    action.printA()
                    for mm in action.mvt:
                        an=mm+"\n"
                        Afichier.write(an)
                    Afichier.close()
                    Ffichier.close()
                    return True
        # on a rien trouver on fait du random
        print("Yolooooo")
        actionRandom = random.randint(1,len(self.A))
        action=self.A[actionRandom]
        action.printA()
        return True


    def printP(self): # affiche toute les actions possibles
        i=0
        for a in self.A:
            print("Action "+str(i)+" => ")
            a.printA()
            i+=1

    def printTRules(self):
        i=0
        for r in self.R:
            print("R "+str(i)+" => ")
            r.printRule()
            i+=1