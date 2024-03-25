package personnage

import item.Armes
import item.Armures
import item.Item

class Guerrier(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armure: Armures?,
    arme: Armes?,
    var armeSecondaire: Armes?,
) : Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse, armure = null, arme = null) {


    /**
     * Cette fonction permet d'équiper une arme principale ou secondaire.
     *
     * @param arme L'arme que vous souhaitez équiper.
     * @author Luca
     */
    override fun equipe(arme: Armes) {
        // Affiche un message demandant à l'utilisateur de choisir entre arme principale (1) ou arme secondaire (2)
        println("1. Arme principale ou 2. Arme secondaire")

        // Lit l'entrée utilisateur
        var choix2 = readln()

        // Vérifie le choix de l'utilisateur
        if (choix2.toInt() == 1) {
            // Équipe l'arme fournie en tant qu'arme principale
            this.arme = arme // Équipe sur l'arme principale
        } else if (choix2.toInt() == 2) {
            // Équipe l'arme fournie en tant qu'arme secondaire
            this.armeSecondaire = arme // Équipe sur l'arme secondaire
        }
    }


    /**
     * Cette fonction permet au personnage d'attaquer un adversaire avec son arme secondaire.
     *
     * @param adversaire Le personnage que le personnage attaque.
     * @author Luca
     */
    override fun attaque(adversaire: Personnage) {
        // TODO: Mission 4.1 - Vous pouvez ajouter une description détaillée de la mission ici

        // Appelle la fonction d'attaque du parent (super) pour effectuer l'action de base du personnage
        super.attaque(adversaire)

        // Vérifie si le personnage possède une arme secondaire
        if (armeSecondaire != null) {
            // Calcule les dégâts en prenant en compte l'attaque du personnage, les dégâts de l'arme secondaire
            // et la défense de l'adversaire
            var degats = this.armeSecondaire!!.calculDegats() + this.attaque - adversaire.defense

            // Assure que les dégâts sont au moins égaux à 1
            if (degats > 1) {
                adversaire.pointDeVie -= degats
                println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
            } else {
                degats = 1
                adversaire.pointDeVie -= degats
                println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
            }
        } else {
            // Si le personnage n'a pas d'arme secondaire, effectue une attaque de base
            if (this.attaque > 0) {
                // Calcule les dégâts en prenant en compte l'attaque du personnage, la moitié des dégâts
                // et la défense de l'adversaire
                var degats = (this.attaque / 2) - adversaire.defense

                // Assure que les dégâts sont au moins égaux à 1
                if (degats > 1) {
                    adversaire.pointDeVie -= degats
                    println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
                } else {
                    degats = 1
                    adversaire.pointDeVie -= degats
                    println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
                }
            }
        }
    }



}