package parking


fun main() {
    val lot = Parking()
    while (true) {
        val command = readln()
        when {
            command.startsWith("park") -> {
                val parsed = command.split(" ")
                val car = Car(parsed[1], parsed[2])
                lot.park(car)
            }

            command.startsWith("leave") -> {
                val slot = command.substring(5).trim().toInt()

                lot.unpark(slot)
            }
            command.startsWith("exit") -> break
        }
    }
}

class Parking {

    private var spot1: Car? = null
    private var spot2: Car? = null

    fun park(car: Car) {
        when {
            spot1 == null -> {
                spot1 = car
                println("${car.color} car parked in slot 1")
            }

            spot2 == null -> {
                spot2 = car
                println("${car.color} car parked in slot 2")
            }

            else -> throw Exception("Not free spots")
        }
    }

    fun unpark(slot: Int) {
        when (slot) {
            1 -> {
                if (spot1 == null) {
                    println("There is no car in slot 1")
                } else {
                    spot1 = null
                    println("Slot 1 is free.")
                }
            }

            2 -> {
                if (spot2 == null) {
                    println("There is no car in slot 2")
                } else {
                    spot2 = null
                    println("Slot 2 is free.")
                }
            }
            else -> throw Exception("Wrong slot!")
        }
    }
}

data class Car(val reg_number: String, val color: String)