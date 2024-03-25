package item

import personnage.Personnage

class Bombe constructor(
    val nombreDeDes :Int,
    val maxDe :Int,
     nom :String,
     description :String
):Item(nom,description){


    /**
     * méthode qui calcul les degats que la bombe infligera
     * @return les dégats
     * @author Antoine
     */
    override fun utiliser(cible : Personnage){
        var protec = 0
        val des = jeu.TirageDes(nombreDeDes , maxDe)
        var degats = des.lance()
        if (cible.armure != null) {
            protec = cible.armure!!.calculProtection()
        }
        degats -= protec
        if (degats<1) degats = 1
        cible.pointDeVie -= degats
        println("la $nom inflige $degats dégats a ${cible.nom}")

    }



}