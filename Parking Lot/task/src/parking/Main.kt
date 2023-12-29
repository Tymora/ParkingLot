package parking


fun main() {
    val lot = Parking()
    while (true) {
        val command = readln()
        val (pCommand, arg) = when {
            command.startsWith("create") -> ParsedCommand.CREATE to command.substring(6)
            command.startsWith("park") -> ParsedCommand.PARK to command.substring(5)
            command.startsWith("leave") -> ParsedCommand.UNPARK to command.substring(5).trim()

            command.startsWith("status") -> ParsedCommand.STATUS to command.substring(5)
            command.startsWith("reg_by_color") -> ParsedCommand.FIND_REG_NUMBER_BY_COLOR to command.substring(
                12
            ).trim()

            command.startsWith("spot_by_color") -> ParsedCommand.FIND_SLOTS_BY_COLOR to command.substring(
                13
            ).trim()

            command.startsWith("spot_by_reg") -> ParsedCommand.FIND_SLOT_BY_REG_NUMBER to command.substring(
                11
            ).trim()

            command.startsWith("exit") -> break
            else -> ParsedCommand.ERROR to "Error"
        }
        lot.doAction(pCommand, arg)
    }


}

class Parking {

    private var spot = arrayOfNulls<Car?>(0)
    private fun createSpot(countSlots: Int) {
        spot = arrayOfNulls(countSlots)
    }


    fun doAction(command: ParsedCommand, argument: String) {
        when (command) {
            ParsedCommand.CREATE -> {
                val countSlots = argument.toInt()
                println("Created a parking lot with $countSlots spots.")
                this.createSpot(if (countSlots > 0) countSlots else 0)
            }

            ParsedCommand.PARK -> {
                val parsed = argument.split(" ")
                val car = Car(parsed[1], parsed[2])
                this.park(car)
            }

            ParsedCommand.UNPARK -> {
                val placeNumber = argument.toInt()
                unpark(placeNumber)
            }

            ParsedCommand.STATUS -> {
                status()
            }

            ParsedCommand.FIND_REG_NUMBER_BY_COLOR -> {
                val parsed = argument.split(" ")
                findColor(parsed[0], parsed[1])
            }

            ParsedCommand.FIND_SLOTS_BY_COLOR -> {
                val parsed = argument.split(" ")
                findColor(parsed[0], parsed[1])
            }

            ParsedCommand.FIND_SLOT_BY_REG_NUMBER -> {
                findRegNumber(argument)
            }

            ParsedCommand.ERROR -> {}

        }
    }

    private fun park(car: Car) {
        var freeSlot = -1
        for (index in spot.indices) {
            if (spot[index] == null) {
                freeSlot = index
                println("${car.color} car parked in slot ${index + 1}.")
                break
            }
        }
        if (freeSlot != -1) {
            spot[freeSlot] = car
        } else println("Not free spots.")

    }

    private fun unpark(placeNumber: Int) {
        val indexSlot = placeNumber - 1
        if (spot[indexSlot] != null) {
            spot[indexSlot] = null
            println("Slot $placeNumber is free.")
        } else println("There is no car in slot $placeNumber")

    }

    private fun status() {
        var check = 0
        for (index in spot.indices) {
            if (spot[index] != null) {
                println("${index + 1} ${spot[index]?.regNumber} ${spot[index]?.color}")
            } else check++
        }
        if (check == spot.size) {
            println("Parking lot is empty.")
        }
    }

    private fun findColor(color: String, command: String) {
        var check = 0
        val output = mutableListOf<String?>()
        for (index in spot.indices) {
            if (spot[index]?.color?.uppercase() == color.uppercase()) {
                if (command.startsWith("reg_by_color")) {
                    output.add(spot[index]?.regNumber)
                } else {
                    output.add((index + 1).toString())
                }
            } else check++
        }
        if (check == spot.size) {
            println("No cars with color $color were found.")
        } else println(output.joinToString(", "))
    }

    private fun findRegNumber(regNum: String) {
        var check = 0
        for (index in spot.indices) {
            if (spot[index]?.regNumber == regNum) {
                println("${index + 1}")
            } else check++
        }
        if (check == spot.size) {
            println("No cars with registration number $regNum were found.")
        }
    }

}

enum class ParsedCommand {
    CREATE,
    PARK,
    UNPARK,
    STATUS,
    FIND_REG_NUMBER_BY_COLOR,
    FIND_SLOTS_BY_COLOR,
    FIND_SLOT_BY_REG_NUMBER,
    ERROR
}

data class Car(val regNumber: String, val color: String)