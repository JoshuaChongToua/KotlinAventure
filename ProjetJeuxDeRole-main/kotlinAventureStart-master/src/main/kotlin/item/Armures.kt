package item

import personnage.Personnage

class Armures(
    nom: String,
    description: String,
    val type: TypeArmure,
    val qualite: Qualite
    ):Item(nom,description) {

    /**
     * méthode qui calcul les bonus de protection
     * @return la protection
     * @author Joshua
     */
    fun calculProtection(): Int {
        return this.type.bonusType + this.qualite.bonusQualite
    }

    override fun utiliser(cible:Personnage) {
        cible.equipe(this)
    }



}