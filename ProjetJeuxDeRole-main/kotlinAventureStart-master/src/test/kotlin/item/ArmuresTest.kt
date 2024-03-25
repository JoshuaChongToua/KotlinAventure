package item

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ArmuresTest {

    @Test
    fun calculProtection() {
        val jTeProtege = Armures("jTeProtege",
            "meme pas mal",
            TypeArmure("cailloux", 132),
            Qualite("legendaire", 3, "\u001B[33m"))

        val resultat = jTeProtege.calculProtection()

        println("result = $resultat")
    }
}