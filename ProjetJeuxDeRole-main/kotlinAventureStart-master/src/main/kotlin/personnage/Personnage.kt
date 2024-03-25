package personnage

import item.*
import jeu.TirageDes

open class Personnage(
    val nom: String,
    var pointDeVie:Int,
    val pointDeVieMax: Int,
    var attaque: Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
    var armure : Armures?,
    var arme : Armes?,
    //val inventaire: MutableList<Item> = mutableListOf<Item>()

    val inventaire: MutableList<Item> = mutableListOf<Item>(
        Armes(
            "Enma",
            description = "Enma est un Meito ayant appartenu à Kozuki Oden, qui l'utilisait de paire avec un autre Meito, Ame no Habakiri. Kozuki Hiyori l'a hérité de son père et c'est le katana qui a donné sa seule cicatrice à Kaido,[2] d'abord avec Oden puis avec Zoro.\n" +
                    "L'épée a été forgée par Shimotsuki Kozaburo.[3] Hiyori l'a donné à Zoro en échange du Shusui.[2]",
            TypeArme("Meito",1,10,10,3),
            Qualite("legendaire", 3, "\u001B[33m"),),

        Armures(
                "jTeProtegePasJSP",
        "une armure indescise",
        TypeArmure("CPeutEtreMoi", 200),
        Qualite("legendaire",3,"\u001B[33m")),

        Armes(
            "shhhhhhhuuuuut",
            "tire des balles silencieuses",
            TypeArme("Gun",3, 80, 54, 2),
            Qualite("legendaire",3,"\u001B"),
        ),

        Armures(
            "HEHEHEHE",
            "une armure rigole",
            TypeArmure("marrant", 30),
            Qualite("legendaire",3,"\u105B[50m"))
        ,
        Bombe(
            nombreDeDes =3,
    maxDe =8,
    nom = "molo",
    description = "sa brule aie"
),
        Bombe(
            nombreDeDes =3,
            maxDe =16,
            nom = "bombe",
            description = "aaaaaaaaaaaaaaaaaaie"
        ),

        Potions(30, "petite potion" , "soigne 30hp"),
        Potions(70, "moyenne potion" , "soigne 70hp")

    )
) {

    /**
     * Cette fonction calcule la défense totale du personnage, en prenant en compte la défense de base
     * et, le cas échéant, la protection fournie par l'armure.
     *
     * @return La valeur de la défense totale du personnage.
     *
     * @author All
     */
    fun calculeDefense(): Int {
        // TODO: Mission 4.2 - Vous pouvez ajouter une description détaillée de la mission ici

        // Vérifie si le personnage porte une armure
        if (armure != null) {
            // Calcule la défense totale en combinant la défense de base et la protection de l'armure,
            // puis en divisant par 2
            return (this.defense + this.armure!!.calculProtection()) / 2
        } else {
            // Si le personnage n'a pas d'armure, la défense est simplement divisée par 2
            return this.defense / 2
        }
    }


    /**
     * Cette fonction permet au personnage d'attaquer un adversaire. Elle prend en compte l'arme équipée
     * (le cas échéant) pour calculer les dégâts de l'attaque.
     *
     * @param adversaire Le personnage que le personnage attaque.
     *
     * @author All
     */
    open fun attaque(adversaire: Personnage) {
        // TODO: Mission 4.1 - Vous pouvez ajouter une description détaillée de la mission ici

        // Vérifie si le personnage porte une arme
        if (arme != null) {
            // Calcule les dégâts en combinant les dégâts de l'arme et la moitié de la valeur d'attaque
            val degats = this.arme!!.calculDegats() + this.attaque / 2
            adversaire.pointDeVie -= degats
            println("$nom attaque ${adversaire.nom} avec ${arme!!.nom} et inflige $degats points de dégâts.")
        } else {
            // Si le personnage n'a pas d'arme, calcule les dégâts en utilisant la moitié de la valeur d'attaque
            var degats = this.attaque / 2

            // Assure que les dégâts sont au moins égaux à 1
            if (degats <= 0) {
                degats = 1
            }

            adversaire.pointDeVie -= degats
            println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
        }
    }


    /**
     * Cette fonction permet au personnage de passer son tour sans effectuer d'action.
     *
     * @author Joshua
     */
    fun passeTour() {
        println("$nom a passé son tour")
    }


    /**
     * Cette fonction affiche les statistiques du personnage, y compris l'arme, l'armure, les points de vie,
     * et, dans le cas d'un Guerrier, l'arme secondaire.
     *
     * @author Joshua
     */
    fun stats() {
        // Affiche l'arme équipée, le cas échéant
        println("Arme : ${this.arme}")

        // Affiche l'armure équipée, le cas échéant
        println("Armure : ${this.armure}")

        // Affiche les points de vie du personnage
        println("PV : ${this.pointDeVie}")

        // Affiche l'attaque du personnage
        println("Attaque : ${this.attaque}")

        // Affiche la défense du personnage
        println("Défense : ${this.defense}")

        // Affiche l'endurance du personnage
        println("Endurance : ${this.endurance}")

        // Affiche la vitesse du personnage
        println("Vitesse : ${this.vitesse}")

        // Si le personnage est un Guerrier, affiche son arme secondaire
        if (this is Guerrier) {
            println("Arme secondaire : ${this.armeSecondaire}")
        }
    }


    /**
     * Cette fonction affiche les statistiques du monstre, y compris l'arme, l'armure, les points de vie,
     * et l'inventaire.
     */
    fun statsMonstre() {
        // Affiche l'arme du monstre, le cas échéant
        println("Arme : ${this.arme}")

        // Affiche l'armure du monstre, le cas échéant
        println("Armure : ${this.armure}")

        // Affiche les points de vie du monstre
        println("PV : ${this.pointDeVie}")

        // Affiche l'inventaire du monstre
        println("Inventaire : ${this.inventaire}")
    }


    /**
     * Cette fonction permet au personnage d'équiper une arme spécifiée depuis son inventaire.
     *
     * @param arme L'arme à équiper.
     */
    open fun equipe(arme: Armes) {
        // Vérifie si l'arme spécifiée se trouve dans l'inventaire du personnage
        if (arme in this.inventaire) {
            this.arme = arme // Équipe l'arme
            println("${this.nom} équipe ${this.arme!!.nom}")
        }
    }


    /**
     * Cette fonction permet au personnage d'équiper une armure spécifiée depuis son inventaire.
     *
     * @param armure L'armure à équiper.
     */
    open fun equipe(armure: Armures) {
        // Vérifie si l'armure spécifiée se trouve dans l'inventaire du personnage
        if (armure in this.inventaire) {
            this.armure = armure // Équipe l'armure
            println("${this.nom} équipe ${this.armure!!.nom}")
        }
    }




    /**
     * Cette fonction permet au personnage de sélectionner un élément de son inventaire pour une action spécifique.
     *
     * @param monstre Le personnage sur lequel l'action sera effectuée (par exemple, une attaque de bombe).
     * @return True si l'action a été validée et effectuée, sinon False.
     *
     * @author Joshua
     */
    fun selctionInventaire(monstre: Personnage): Boolean {
        var selection: String
        val taille = this.inventaire.size.toString()
        var indexValide = true
        var item: Item? = null

        do {
            println("Sélectionnez un item (0 pour annuler) : ")
            selection = readln()
            val indexItem = selection.toInt()

            // Vérifie si l'index sélectionné est valide et lève une exception si ce n'est pas le cas
            try {
                item = this.inventaire[indexItem]
                indexValide = true
            } catch (erreur: Exception) {
                indexValide = false
            }
        } while (selection != "0" && (!indexValide || indexItem < 0 || indexItem >= this.inventaire.size))

        if (selection == "0") {
            return false
        }

        var actionValide = false

        // Si l'élément est une bombe, elle est utilisée contre le monstre
        if (item is Bombe) {
            var cible = monstre
            item.utiliser(cible)
            this.inventaire.remove(item)
            actionValide = true
        }
        // Si l'élément est une potion, elle est utilisée sur le personnage lui-même
        else if (item is Potions) {
            val cible = this
            item.utiliser(cible)
            this.inventaire.remove(item)
            actionValide = true
        }

        // Si le personnage est un Guerrier et l'élément est une arme, il l'équipe
        if (this is Guerrier) {
            if (item is Armes) {
                this.equipe(item)
            }
        } else {
            // Pour les autres types de personnages, l'objet est utilisé directement sur le personnage
            item?.utiliser(this)
        }

        return actionValide
    }



    /**
     * Cette fonction permet au personnage d'ouvrir son inventaire, d'afficher son contenu, et de sélectionner une action à partir de son inventaire.
     *
     * @param monstre Le personnage sur lequel l'action sera effectuée (par exemple, une attaque de bombe).
     * @return True si l'action a été validée et effectuée, sinon False.
     *
     * @author Joshua
     */
    fun ouvrirInventaire(monstre: Personnage): Boolean {
        println("Inventaire de ${this.nom}")

        // Affiche le contenu de l'inventaire avec des numéros d'index pour chaque élément
        for (i in 1 until this.inventaire.size) {
            println("$i. ${this.inventaire[i]}")
        }

        println("0. Quitter !!!")

        // Appelle la fonction selctionInventaire pour sélectionner et effectuer une action depuis l'inventaire
        return selctionInventaire(monstre)
    }


    /**
     * Cette fonction vérifie si le personnage possède au moins une potion dans son inventaire.
     *
     * @return True si le personnage a au moins une potion, sinon False.
     * @author Antoine
     */
    fun avoirPotion(): Boolean {
        var potions = false

        // Parcourt l'inventaire du personnage
        for (elt in inventaire) {
            // Vérifie si l'élément est une potion
            if (elt is item.Potions) {
                potions = true
            }
        }

        return potions
    }


    /**
     * Cette fonction vérifie si le personnage possède au moins une bombe dans son inventaire.
     *
     * @return True si le personnage a au moins une bombe, sinon False.
     * @author Antoine
     */
    fun avoirBombe(): Boolean {
        var bombe = false

        // Parcourt l'inventaire du personnage
        for (elt in inventaire) {
            // Vérifie si l'élément est une bombe
            if (elt is item.Bombe) {
                bombe = true
            }
        }

        return bombe
    }


    /**
     * Cette fonction gère la possibilité de récupérer l'inventaire d'un adversaire si sa santé atteint un seuil critique.
     *
     * @param adversaire Le personnage adversaire.
     * @author Joshua Luca
     */
    fun loot(adversaire: Personnage) {
        // Vérifie si la santé de l'adversaire est inférieure à 0 (supposant que cela signifie qu'il est vaincu)
        if (adversaire.pointDeVie < 0) {
            val de = TirageDes(1, 10)
            val result = de.lance()

            // Si le résultat du dé est 0 ou 1, le personnage a la possibilité de récupérer l'inventaire de l'adversaire
            if (result == 1 || result == 0) {
                println("Félicitations, vous avez looté l'inventaire de ${adversaire.nom}")
                println(adversaire.inventaire)
                this.inventaire += adversaire.inventaire // Ajoute l'inventaire de l'adversaire à votre propre inventaire
            }
        }
    }


    /**
     * Cette fonction permet au personnage de boire une potion pour récupérer des points de vie s'il en a dans son inventaire.
     * @author Antoine
     */
    fun boirePotion() {
        // Vérifie si le personnage a au moins une potion dans son inventaire
        if (avoirPotion()) {
            // Parcourt l'inventaire du personnage
            for (elt in inventaire) {
                // Vérifie si l'élément est une potion
                if (elt is item.Potions) {
                    pointDeVie += elt.soin // Ajoute les points de soin de la potion aux points de vie du personnage

                    // Si les points de vie dépassent le maximum, les ajuste au maximum
                    if (pointDeVie > pointDeVieMax) {
                        pointDeVie = pointDeVieMax
                    }
                }
            }
        }
    }







    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse, arme: $arme, armure: $armure)"
    }


}