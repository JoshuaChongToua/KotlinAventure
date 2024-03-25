package item


import jeu.TirageDes
import personnage.Personnage

class Armes (
    name : String,
    description : String,
    val type : TypeArme,
    val qualite : Qualite,

):Item(name,description){


    /**
     * méthode qui calcul les degats que l'arme infligera
     * @return les dégats
     * @author Luca
     */
    fun calculDegats():Int{
        var desDegat = TirageDes(this.type.nombreDes,this.type.valeurDeMax)
        var desCrit = TirageDes(1,20)
        var degats = 0
        if (desCrit.lance()  >= this.type.activationCritique){ // Si le résultat du lancer est >= activationCritique du type d'arme alors le coup est critique
            degats = desDegat.lance()*this.type.multiplicateurCritique+this.qualite.bonusQualite // Dégats correspond au lanceur * multiplicateur critique de l'arme
            println("Coup Critique")
        }
        else {
            degats = desDegat.lance()+this.qualite.bonusQualite
        }
        return degats
    }

    override fun utiliser(cible: Personnage){
        cible.equipe(this)
    }



}


























































