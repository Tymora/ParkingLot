package parking


fun main() {
    var countSlots = 0
    val lot = Parking(countSlots)
    while (countSlots == 0) {
        val createInput = readln()
        when {
            createInput.startsWith("create") -> countSlots = createInput.padEnd(3).toInt() // >_< Exception
            else -> println("Sorry, a parking lot has not been created.")
            // todo Нужно добавить проверку что вводится значение выше нуля
        }
    }
    while (true) {
        val command = readln()
        when {
            command.startsWith("park") -> {
                val parsed = command.split(" ")
                val car = Car(parsed[1], parsed[2])
                lot.park(car)
            }

            command.startsWith("leave") -> {
                val placeNumber = command.substring(5).trim().toInt() // А если цифра больше 9?

                lot.unpark(placeNumber)
            }
            command.startsWith("status") -> lot.status(countSlots)
            command.startsWith("exit") -> break
        }
    }
}

class Parking(countSlots: Int) {


    private var spot = arrayOfNulls<Car?>(countSlots)


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

    fun status (countSlots: Int){
        var check = 0
        for (item in spot){
            if (item != null){
                println(item)
            } else check++
        }
        if(check == countSlots){
            println("Parking lot is empty")
        }
    }
}

data class Car(val reg_number: String, val color: String)