package item

import personnage.Personnage

open class Item  (
    val nom : String,
    val description : String)
{

    open fun utiliser(cible: Personnage){
        println("$nom ne peut pas etre utilisé.")
    }

    override fun toString(): String {
        return "${nom} (nom=$nom', description = '$description')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false

        if (nom != other.nom) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nom.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }


}
