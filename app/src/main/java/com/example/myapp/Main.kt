open class Piece() {
    open val nom: String = ""
    open val longeur: Double = 0.0
    open val largeur: Double = 0.0
    fun surface(): Double {
        return largeur * longeur
    }
}

class Salon: Piece(){
    override val nom = "Salon"
    override val largeur = 18.0
    override val longeur= 2.0
}

class Cuisine: Piece(){
    override val nom = "Cuisine"
    override val largeur = 10.0
    override val longeur= 2.0
}




fun main(){
    val listePiece = (listOf(Salon(), Cuisine()))
    for (piece in listePiece){
        println(piece.nom)
        println(piece.surface())
    }
}