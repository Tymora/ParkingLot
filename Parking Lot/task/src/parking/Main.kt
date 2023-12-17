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
                val placeNumber = command.substring(5).trim().toInt()

                lot.unpark(placeNumber)
            }
            command.startsWith("exit") -> break
        }
    }
}

class Parking {


    private var spot =  arrayOfNulls<Car?>(20)


    fun park(car: Car) {
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

    fun unpark(placeNumber: Int) {
        val indexSlot = placeNumber - 1
        if (spot[indexSlot] != null) {
            spot[indexSlot] = null
            println("Slot $placeNumber is free.")
        } else println("There is no car in slot $placeNumber")

    }
}

data class Car(val reg_number: String, val color: String)