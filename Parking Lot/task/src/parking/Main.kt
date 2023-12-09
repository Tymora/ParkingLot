package parking



fun main() {
    val lot = Parking()
    var parsing = mutableListOf<String>()
    parsing = readln().split(" ").toMutableList()
    when (parsing[0]) {
        "park" -> lot.park()
        "leave" -> lot.unpark()
    }
}

class Parking(var parsing: MutableList<String>) {

    var spot1: Car? = null
    var spot2: Car? = null

    fun park() {
        when {
            spot1 == null -> spot1 = Car(parsing[1], parsing[2])
            spot2 == null -> spot2 = Car(parsing[1], parsing[2])
            else -> throw Exception("Not free spots")
        }
    }

    fun unpark() {
        when (parsing[1]) {
            "1" -> spot1 = null
            "2" -> spot1 = null
        }
        println("Spot ${parsing[1]} is free.")
    }
}

data class Car(val reg_number: String?, val color: String?)