package parking

var countSlots = 0
fun main() {
    val lot = Parking()
    var spot = arrayOfNulls<Car?>(countSlots)
    while (true) {
        val command = readln()
        when {
            command.startsWith("create") -> {
                countSlots = command.substring(6).trim().toInt()
                println("Created a parking lot with $countSlots spots.")
                spot(countSlots) ///АРРРГХ!
                // todo Нужно добавить проверку что вводится значение выше нуля
            }
            command.startsWith("park") -> {
                val parsed = command.split(" ")
                val car = Car(parsed[1], parsed[2])
                lot.park(car, spot)
            }

            command.startsWith("leave") -> {
                val placeNumber =
                    command.substring(5).trim().toInt()

                lot.unpark(placeNumber, spot)
            }
            command.startsWith("status") -> lot.status(countSlots, spot)
            command.startsWith("exit") -> break
            else -> println("Sorry, a parking lot has not been created.")
        }
    }
}

class Parking {

    fun park(car: Car, spot: Array<Car?>) {
        var freeSlot = -1
        for (index in spot.indices) {
            if (spot[index] == null) {
                freeSlot = index
                println("${car.color} car parked in slot ${index + 1}")
                break
            }
        }
        if (freeSlot != -1) {
            spot[freeSlot] = car
        } else println("Not free spots")

    }

    fun unpark(placeNumber: Int, spot: Array<Car?>) {
        val indexSlot = placeNumber - 1
        if (spot[indexSlot] != null) {
            spot[indexSlot] = null
            println("Slot $placeNumber is free.")
        } else println("There is no car in slot $placeNumber")

    }

    fun status(countSlots: Int, spot: Array<Car?>) {
        var check = 0
        for (index in spot.indices) {
            if (spot[index] != null) {
                println("${index + 1} ${spot[index]?.reg_number} ${spot[index]?.color}")
            } else check++
        }
        if (check == countSlots) {
            println("Parking lot is empty")
        }
    }
}

data class Car(val reg_number: String, val color: String)