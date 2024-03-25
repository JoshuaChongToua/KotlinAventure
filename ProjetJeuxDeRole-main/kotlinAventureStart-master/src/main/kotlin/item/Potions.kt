package item

import personnage.Personnage

class Potions constructor(
    val soin :Int,
    nom :String,
    description :String): Item(nom, description){

     override fun utiliser (cible: Personnage){
         cible.boirePotion()
    }
}