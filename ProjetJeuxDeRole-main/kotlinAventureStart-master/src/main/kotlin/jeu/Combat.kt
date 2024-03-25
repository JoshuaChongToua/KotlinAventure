package jeu

import personnage.Guerrier
import personnage.Personnage
import personnage.Voleur
import personnage.Mage

class Combat(
    val jeu: Jeu,
    val monstre: Personnage
) {
    var nombreTours: Int = 1

    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {
        println("\u001B[34m ---Tour de ${this.jeu.joueur.nom} (pv: ${this.jeu.joueur.pointDeVie}) ---")

        //TODO Mission 1.2
        var actionValide = false
        while (!actionValide) {
            if (this.jeu.joueur is Voleur) {
                println("0. Voler 1. Attaquer  2. Passer  3. Inventaire  4. Stats")
            } else if (this.jeu.joueur is Mage) {
                println("0. Lancer Sort  1. Attaquer  2. Passer  3. Inventaire  4. Stats")
            } else
                println("1. Attaquer  2. Passer  3. Inventaire  4. Stats")

            var action = readln()// Entrer choix d'action

            while (action !in ("0".."5")) {
                if (this.jeu.joueur is Voleur) {
                    println("0. Voler 1. Attaquer  2. Passer  3. Inventaire  4. Stats")
                } else if (this.jeu.joueur is Mage) {
                    println("0. Lancer Sort  1. Attaquer  2. Passer  3. Inventaire  4. Stats")
                } else
                    println("1. Attaquer  2. Passer  3. Inventaire  4. Stats")

                action = readln()
            }

            if (action == "1") {// Si choix d'action est Attaquer
                this.jeu.joueur.attaque(monstre)
                action = "Attaquer"
                actionValide = true
            } else if (action == "2") {// Si choix d'action est passer le tour
                this.jeu.joueur.passeTour()
                actionValide = true
                action = "Passe le tour"
            } else if (action == "3") {// Si choix d'action est ouvrir l'inventaire
                actionValide = this.jeu.joueur.ouvrirInventaire(monstre)
                action = ""
            } else if (action == "4") {// Si choix d'action est passer le tour
                this.jeu.joueur.stats()
                action = "Stats et equipements du personnage"
            } else if (action == "5") {// Si choix d'action est passer le tour
                this.monstre.statsMonstre()
                action = "Stats et equipements du personnage"
            } else if (this.jeu.joueur is Voleur && action == "0") {
                val valide = (this.jeu.joueur as Voleur).volerItem(monstre)
                actionValide = valide
            } else if (this.jeu.joueur is Mage && action == "0") {
                (this.jeu.joueur as Mage).choisirEtLancerSort(monstre)
                actionValide = true
            }

            println(action)
            println("\u001b[0m")
        }
    }


    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        var des = TirageDes(1, 100) // Tire un nombre aléatoire entre 1 et 100
        println("\u001B[31m---Tour de ${monstre.nom} (pv: ${monstre.pointDeVie}) ---") // affiche le tour du monstre
        //TODO Mission 1.3
        if (des.lance() <= 70) { // Si le nombre tiré est inférieur à 70 le monstre attaque
            this.monstre.attaque(this.jeu.joueur)
        } else if (des.lance() > 70) { // Si le nombre tiré est superieur à 70 le monstre passe son tour
            this.monstre.passeTour()
        }
        println("\u001b[0m")
        for (elt in monstre.inventaire) {
            if (elt is item.Potions)
                if (monstre.pointDeVie < monstre.pointDeVieMax / 2) {
                    val d = TirageDes(1, 10)
                    val tirage = d.lance()
                    if (tirage == 1) {
                        elt.utiliser(monstre)
                        monstre.inventaire.remove(elt)
                    }
                }

        }
    }

    // Méthode pour exécuter le combat complet
    fun executerCombat() {
        println("Début du combat : ${this.jeu.joueur.nom} vs ${monstre.nom}")
        //La vitesse indique qui commence
        var tourJoueur = this.jeu.joueur.vitesse >= this.monstre.vitesse
        //Tant que le joueur et le monstre sont vivants
        while (this.jeu.joueur.pointDeVie > 0 && monstre.pointDeVie > 0) {
            println("Tours de jeu : ${nombreTours}")
            if (tourJoueur) {
                tourDeJoueur()
            } else {
                tourDeMonstre()
            }
            nombreTours++
            tourJoueur = !tourJoueur // Alternance des tours entre le joueur et le monstre
            println("${this.jeu.joueur.nom}: ${this.jeu.joueur.pointDeVie} points de vie | ${monstre.nom}: ${monstre.pointDeVie} points de vie")
            println("")
        }

        if (this.jeu.joueur.pointDeVie <= 0) {
            println("Game over ! ${this.jeu.joueur.nom} a été vaincu !")
            println("Le combat recommence")

            this.jeu.joueur.pointDeVie = this.jeu.joueur.pointDeVieMax
            this.monstre.pointDeVie = this.monstre.pointDeVieMax
            this.executerCombat()
        } else {
            println("BRAVO ! ${monstre.nom} a été vaincu !")
            this.jeu.joueur.loot(monstre)
        }
    }
}