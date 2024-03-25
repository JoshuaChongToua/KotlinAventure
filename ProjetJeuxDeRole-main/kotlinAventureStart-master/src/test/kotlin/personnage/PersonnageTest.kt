package personnage

import item.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PersonnageTest {

    @Test
    fun attaque() {
        val Zoro = Personnage(
            " Roronoa Zoro ",
            pointDeVie = 900,
            pointDeVieMax = 900,
            attaque = 40,
            defense = 39,
            vitesse = 40,
            endurance = 40,
            arme = Armes(
                "Enma",
                description = "Enma est un Meito ayant appartenu à Kozuki Oden, qui l'utilisait de paire avec un autre Meito, Ame no Habakiri. Kozuki Hiyori l'a hérité de son père et c'est le katana qui a donné sa seule cicatrice à Kaido,[2] d'abord avec Oden puis avec Zoro.\n" +
                        "L'épée a été forgée par Shimotsuki Kozaburo.[3] Hiyori l'a donné à Zoro en échange du Shusui.[2]",
                TypeArme(1,10,10,3),
                Qualite("legendaire", 3, "\u001B[33m"),)
            ,
            armure = Armures(
                "jTeProtegePasJSP",
                "une armure indescise",
                TypeArmure("CPeutEtreMoi", 200),
                Qualite("legendaire",3,"\u001B[33m")
            )
        )
        val gobelin = Personnage(
            "XXX le gobelin",
            pointDeVie = 20,
            pointDeVieMax = 20,
            attaque = 5,
            defense = 4,
            vitesse = 11,
            endurance = 6,
            arme = Armes(
                "shhhhhhhuuuuut",
                "tire des balles silencieuses",
                TypeArme(3, 80, 54, 2),
                Qualite("legendaire",3,"\u001B"),
            ),
            armure = Armures(
                "jTeProtege",
                "meme pas mal",
                TypeArmure("cailloux", 132),
                Qualite("legendaire",3,"\u001B")
            )

        )
        val resultat = Zoro.attaque(gobelin)
        println("result = $resultat ")
    }

    @Test
    fun calculDefense() {

        val gobelin = Personnage(
            "XXX le gobelin",
            pointDeVie = 20,
            pointDeVieMax = 20,
            attaque = 5,
            defense = 4,
            vitesse = 11,
            endurance = 6,
            arme = Armes(
                "shhhhhhhuuuuut",
                "tire des balles silencieuses",
                TypeArme(3, 80, 54, 2),
                Qualite("legendaire",3,"\u001B"),
            ),
            armure = Armures(
                "jTeProtege",
                "meme pas mal",
                TypeArmure("cailloux", 132),
                Qualite("legendaire",3,"\u001B")
            ))

        val def = gobelin.calculeDefense()
        print("la defense est de $def")

    }
}