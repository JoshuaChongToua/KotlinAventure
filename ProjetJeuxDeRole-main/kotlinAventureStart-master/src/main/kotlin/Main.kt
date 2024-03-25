import build.BardService2
import item.*
import jeu.Jeu
import jeu.TirageDes
import personnage.Personnage
import personnage.Sort

//instanciation des qualités des objets
val qualiteCommun = Qualite("commun", 0, "\u001B[32m")
val qualiteRare = Qualite("rare", 1, couleur = "\u001B[34m")
val qualiteEpic = Qualite("epic", 2, "\u001B[35m")
val qualiteLegendaire = Qualite("legendaire", 3, "\u001B[33m")

// Armes

val shhhhhhhuuuuut = Armes(
    "shhhhhhhuuuuut",
    "tire des balles silencieuses",
    TypeArme("Gun",3, 50, 2, 2),
    qualiteLegendaire,
)

val epee_dophile = Armes(
    name = "l'Epee d'ophile",
    description = "l'epee preféré de tk , norman et amaru",
    type = TypeArme("Epee tres longue", 78, 5000000, 45, 1),
    qualiteLegendaire,
)

val enma = Armes(
    "Enma",
    description = "Enma est un Meito ayant appartenu à Kozuki Oden, qui l'utilisait de paire avec un autre Meito, Ame no Habakiri. Kozuki Hiyori l'a hérité de son père et c'est le katana qui a donné sa seule cicatrice à Kaido,[2] d'abord avec Oden puis avec Zoro.\n" +
            "L'épée a été forgée par Shimotsuki Kozaburo.[3] Hiyori l'a donné à Zoro en échange du Shusui.[2]",
    TypeArme("Meito",1,100,13,3),
    qualiteLegendaire,
)

val ameNoHabakiri = Armes(
    "天羽々斬",
    description = "Meito ayant appartenu à Kozuki Oden, qui l'utilisait avec un autre Meito, Enma.",
    TypeArme("Meito",1,110,15,2),
    qualiteLegendaire
)



// Armures

val jTeProtege = Armures(
    "jTeProtege",
    "meme pas mal",
    TypeArmure("cailloux", 132),
    qualiteLegendaire
)

val antiPolice = Armures(
    "evite la prison",
    "peux utiler l'epee sans risquer la prison",
    TypeArmure("enfants", 999999999),
    qualiteLegendaire
)

val JsaisPas = Armures(
    "jTeProtegePasJSP",
    "une armure indescise",
    TypeArmure("CPeutEtreMoi", 200),
    qualiteLegendaire
)

// sorts



val projectionAcide = Sort ( "Sort de projection acide") { caster, cible ->
    run {
        val des = TirageDes(1, 100)
        var degat = des.lance()
        degat = maxOf(1, degat - cible.calculeDefense())

        cible.pointDeVie -= degat

        println("Le jet d'acide inflige $degat à ${cible.nom}")
    }
}

val kamehameha = Sort ( "kamehameha") { caster, cible ->
    run {
        val des = TirageDes(5, 500)
        var degat = des.lance()
        degat = maxOf(1, degat - cible.calculeDefense())

        cible.pointDeVie -= degat

        println("KAMEHAEHAAAAAAAAAAAAAAAAAAAAAA inflige $degat degats à ${cible.nom}")
    }
}

 var compteur = 0

val tornadeDeFeu = Sort ( "Tornade de feu") { caster, cible ->
    run {
        if (compteur<caster.attaque/2) {
            val des = TirageDes(5, 800)
            var degat = des.lance()
            degat = maxOf(1, degat - cible.calculeDefense())

            cible.pointDeVie -= degat

            println("La tornade de feu inflige $degat degats à ${cible.nom}")
        }
        else println("plus asser de Puissance pour faire la tornade de feu")
    }
}

val Soin = Sort ( "Soin",) { caster, cible ->
    run {
            val des = TirageDes(1, 6)
            var soin = des.lance()


            cible.pointDeVie += soin + cible.attaque/2
        if (cible.pointDeVie>cible.pointDeVieMax) cible.pointDeVie=cible.pointDeVieMax

            println("Les pv de ${cible.nom} sont de ${cible.pointDeVie}/${cible.pointDeVieMax} ")
        }

    }


val invocationArme = Sort ("Invocation d'arme"){ caster, cible ->
    run {
    var de = TirageDes(1,20)
    var resultat = de.lance()
    var qualite : Qualite
    var arme : Armes
    if (resultat<5){
        qualite = qualiteCommun
        arme = Armes("Curdent","Inutile pas de chance TOI", TypeArme("Pique",1,10,2,9),qualite)
    }
    else if (resultat<10){
        qualite = qualiteRare
        arme = Armes("Epee en bois","Arme vraiiiiiment nuuul", TypeArme("Epee longue",1,20,3,7),qualite)

    }
    else if (resultat<15){
        qualite = qualiteEpic
        arme = Armes("Eden","L'épee d'Eden", TypeArme("Epee magique",3,15,5,5),qualite)

    }
    else {
        qualite = qualiteLegendaire
        arme = Armes("Yoru","Un sabre qui fait partie des Sabres les plus puissants au monde.", TypeArme("sabre",5,25,10,3),qualite)
    }
    cible.inventaire.add(arme)
    if (arme in cible.inventaire) {
        println("${arme.nom}  est ajoutée à l'inventaire")
    }

}
}

val invocationArmure = Sort ("invocation d'armure bb "){caster , cible ->
    run {
    var de = TirageDes(1,20)
    var resultat = de.lance()
    var qualite : Qualite
    var armure : Armures
    if (resultat<5){
        qualite = qualiteCommun
        armure = Armures("tout nue","t'es a poil", TypeArmure("nue",2),qualite)
    }
    else if (resultat<10){
        qualite = qualiteRare
        armure = Armures("costard cravate","pro le boug", TypeArmure("costard",5),qualite)
    }
    else if (resultat<15){
        qualite = qualiteEpic
        armure = Armures("ensemble nike","le flow ", TypeArmure("nike",8),qualite)
    }
    else {
        qualite = qualiteLegendaire
        armure = Armures("rudy gobert","meilleur defenseur", TypeArmure("gobzilla",15),qualite)
    }
    cible.inventaire.add(armure)

    if (armure in cible.inventaire) {
        println("${armure.nom} est ajoutée à l'inventaire")
    }

}
}




fun main(args: Array<String>) {

    //Instantiation des monstres
    val gobelin = Personnage(
        "XXX le gobelin",
        pointDeVie = 20,
        pointDeVieMax = 20,
        attaque = 5,
        defense = 0,
        vitesse = 11,
        endurance = 6,
        arme = shhhhhhhuuuuut,
        armure = null,
        inventaire = mutableListOf(
            Armes(
                "shhhhhhhuuuuut",
        "tire des balles silencieuses",
                TypeArme("Gun",3, 50, 2, 2),
                qualiteLegendaire,
                )
        )

    )

    val cyclope = Personnage(
        "BigC le Cyclope",
        pointDeVie = 100,
        pointDeVieMax = 100,
        attaque = 4,
        defense = 7,
        vitesse = 2,
        endurance = 4,
        armure = jTeProtege,
        arme = shhhhhhhuuuuut
    )

    val Zoro = Personnage(
        " Roronoa Zoro ",
        pointDeVie = 100000,
        pointDeVieMax = 100000,
        attaque = 100000,
        defense = 100000,
        vitesse = 100000,
        endurance = 100000,
        arme = enma,
        armure = JsaisPas

    )

    val destructor = Personnage(
        "le pere vert",
        pointDeVie = 99999,
        pointDeVieMax = 999999,
        attaque = 9999999,
        defense = 99999,
        vitesse = 999999999,
        endurance = 0,
        arme = epee_dophile,
        armure= antiPolice,
        )

    /*print("Prologue\n" +
            "\n" +
            "Au-delà des montagnes écarlates, dans les terres maudites d'Azrath, se trouvent des légendes qui effraient même les plus braves. \n" +
            "Des monstres que même le ciel n'ose affronter, et des ombres dont la simple mention glace le sang. \n" )
    print(  "\nLa légende raconte que quatre créatures puissantes, autrefois des héros, ont été corrompues par le pouvoir du cristal noir." +
            "\nCes quatre êtres, maintenant méchants, cherchent à conquérir les terres de l'Est et à instaurer un règne de terreur. \n")


    val gpt= GPTService()
    var texte = gpt.fetchHistoire("cree une histoire en utilisant le ${cyclope.nom} qui est méchant")
    */

    var bard = BardService2()
    var texte = bard.fetchHistoire("fait 1+1")

    // TODO Intermission 1 Ajouter d'autres monstres
    //On ajoute les monstres a la liste de monstres du jeu
    val jeu = Jeu(listOf( gobelin,cyclope,Zoro,destructor,))
    //Lancement du jeu

    jeu.lancerCombat()
}
