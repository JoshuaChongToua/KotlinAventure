package personnage

import item.Armes
import item.Armures
import item.Item
import jeu.TirageDes

class Voleur (
    nom: String,
    pointDeVie:Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armure : Armures?,
    arme : Armes?
    ):Personnage(nom,pointDeVie,pointDeVieMax,attaque,defense,endurance,vitesse,armure=null,arme=null,)
{
    /**
     * Cette fonction permet au personnage de voler un item à l'adversaire (si l'adversaire a un inventaire).
     *
     * @param cible Le personnage adversaire.
     * @return True si l'item a été volé, sinon False.
     * @author Joshua
     */
    fun volerItem(cible: Personnage): Boolean {
        // Vérifie si l'inventaire de l'adversaire n'est pas vide
        if (cible.inventaire.isNotEmpty()) {
            val de = TirageDes(0, cible.inventaire.size - 1)
            var voler: Item
            val tirage = de.lance() // Lance un dé pour obtenir un nombre aléatoire correspondant à l'index d'un item de l'adversaire
            var item = cible.inventaire[tirage]

            // Si l'item tiré est l'arme principale de l'adversaire, déposez l'arme de l'adversaire
            if (item.equals(cible.arme)) {
                cible.arme = null
            } else if (item.equals(cible.armure)) {
                cible.armure = null
            }

            voler = cible.inventaire[tirage]
            this.inventaire.add(cible.inventaire[tirage]) // Ajoute l'item volé à notre inventaire
            cible.inventaire.remove(cible.inventaire[tirage]) // Retire l'item de l'inventaire de l'adversaire

            println("$voler Volé")
            return true
        } else {
            println("Inventaire de la cible VIDE")
            return false
        }
    }


}