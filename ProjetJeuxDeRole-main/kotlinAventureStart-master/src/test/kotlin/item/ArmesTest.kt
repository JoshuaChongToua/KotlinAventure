package item

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ArmesTest {

    @Test
    fun calculDegats() {
        val enma = Armes(
            "Enma",
            description = "Enma est un Meito ayant appartenu à Kozuki Oden, qui l'utilisait de paire avec un autre Meito, Ame no Habakiri. Kozuki Hiyori l'a hérité de son père et c'est le katana qui a donné sa seule cicatrice à Kaido,[2] d'abord avec Oden puis avec Zoro.\n" +
                    "L'épée a été forgée par Shimotsuki Kozaburo.[3] Hiyori l'a donné à Zoro en échange du Shusui.[2]",
            TypeArme("Meito",1,10,10,3),
            Qualite("legendaire", 3, "\u001B[33m"),
        )
        val resultat = enma.calculDegats()
        println("result = $resultat ")
    }
}