package personnage

import item.Armes
import item.Armures


class Mage (
    nom: String,
    pointDeVie:Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armure : Armures?,
    arme : Armes?,
    val grimoire:MutableList<Sort> = mutableListOf<Sort>(),


    ):Personnage(nom,pointDeVie,pointDeVieMax,attaque,defense,endurance,vitesse,armure=null,arme=null,)
{

    /**
     * Cette fonction affiche le grimoire, une liste d'éléments, en montrant le nom de chaque élément
     * accompagné de son indice dans le grimoire.
     *
     * @author Antoine
     */
    fun afficherGrimoire() {
        var n = 0

        // Parcours de la liste grimoire
        for (elt in grimoire) {
            // Affiche l'indice (n) suivi du nom de l'élément (elt.nom)
            println("$n => ${elt.nom}")
            n++
        }
    }


    /**
     * Cette fonction permet au personnage de choisir un sort du grimoire, de sélectionner une cible (joueur ou monstre),
     * et de lancer le sort sur la cible choisie.
     *
     * @param cible La cible sur laquelle le sort sera lancé.
     *
     * @author Antoine
     */
    fun choisirEtLancerSort(cible: Personnage) {
        // Affiche le contenu du grimoire
        afficherGrimoire()
        print("Choisissez un sort : ")

        // Lit l'indice du sort choisi par l'utilisateur
        var choix = readln().toInt()

        // Vérifie si l'indice est valide (entre 0 et la taille du grimoire - 1)
        while (choix > grimoire.size - 1 || choix < 0) {
            print("Choisissez un sort valide : ")
            choix = readln().toInt()
        }

        // Récupère le sort choisi à partir du grimoire
        var sortChoisi: Sort = this.grimoire[choix]

        print("Choisissez la cible (1. joueur ou 2. monstre) : ")
        val cibleChoix = readln()
        var cibleSort: Personnage = cible

        // Sélectionne la cible en fonction du choix de l'utilisateur
        if (cibleChoix.toInt() == 1) {
            cibleSort = this // Le joueur est la cible
        } else if (cibleChoix.toInt() == 2) {
            cibleSort = cible // Le monstre est la cible
        }

        // Lance le sort choisi sur la cible sélectionnée
        sortChoisi.effet(this, cibleSort)
    }


    override fun toString(): String {
        return "Mage(${super.toString()} ,grimoire=$grimoire)"
    }


}