package parking

fun main() {
    val lot = Parking()
    val input1 = readln()
    while (true) {
        lot.park()
    }

}

class Parking() {
    var spot1 = mutableListOf<String>()
    var spot2 = mutableListOf<String>()

    fun park() {
        if (spot1.isEmpty()) {
            spot1 = readln().split(" ").toMutableList()
            println("${spot1[2]} car parked in spot 1")
        } else if (spot2.isEmpty()) {
            spot2 = readln().split(" ").toMutableList()
            println("${spot2[2]} car parked in spot 2")
        } else println("Not free spots")
    }

    fun unpark(input1: String) {
        when (input1) {
            "leave 1" -> if (spot1.isNotEmpty()) {
                spot1.clear()
            } else {
                println("There is no car in spot 1.")
            }
            "leave 2" -> if (spot2.isNotEmpty()) {
                spot2.clear()
            } else {
                println("There is no car in spot 2.")
            }
        }

    }

}