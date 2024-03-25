package item

import org.junit.jupiter.api.Test

import personnage.Personnage

class BombeTest {

    @Test
    fun utiliser() {



        //Instantiation des monstres
        val gobelin = Personnage(
            "XXX le gobelin",
            pointDeVie = 20,
            pointDeVieMax = 20,
            attaque = 5,
            defense = 0,
            vitesse = 11,
            endurance = 6,
            arme = Armes(
                "shhhhhhhuuuuut",
                "tire des balles silencieuses",
                TypeArme(3, 80, 54, 2),
                Qualite("legendaire", 3, "\u001B[33m")),

            armure = null,
            inventaire = mutableListOf<Item>() )



        val molo = Bombe(
            nombreDeDes =3,
            maxDe =8,
            nom = "molo",
            description = "sa brule aie"
        )

           molo.utiliser(gobelin)
    }
}