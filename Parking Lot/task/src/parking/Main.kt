package parking

var countSlots = 0
fun main() {
    val lot = Parking()

    while (true) {
        val command = readln()
        if (countSlots == 0 && !command.startsWith("create")) {
            println("Sorry, a parking lot has not been created.")
        } else {
            when {
                command.startsWith("create") -> {
                    countSlots = command.substring(6).trim().toInt()
                    println("Created a parking lot with $countSlots spots.")
                    lot.createSpot(if (countSlots > 0) countSlots else 0)
                }
                command.startsWith("park") -> {
                    val parsed = command.split(" ")
                    val car = Car(parsed[1], parsed[2])
                    lot.park(car)
                }

                command.startsWith("leave") -> {
                    val placeNumber =
                        command.substring(5).trim().toInt()

                    lot.unpark(placeNumber)
                }
                command.startsWith("reg_by_color") -> {
                    val color = command.substring(12).trim()
                    lot.findColor(color, command)
                }
                command.startsWith("spot_by_color") -> {
                    val findColor = command.substring(13).trim()
                    lot.findColor(findColor, command)
                }
                command.startsWith("spot_by_reg") -> {
                    val regNum = command.substring(11).trim()
                    lot.findRegNumber(regNum)
                }
                command.startsWith("status") -> lot.status(countSlots)
                command.startsWith("exit") -> break
                else -> println("Sorry, input is incorrect.") // может быть должна быть другая ошибка?
            }
        }
    }
}

class Parking {
    private var spot = arrayOfNulls<Car?>(0)
    fun createSpot(countSlots: Int) {
        spot = arrayOfNulls(countSlots)
    }

    fun park(car: Car) {
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

    fun unpark(placeNumber: Int) {
        val indexSlot = placeNumber - 1
        if (spot[indexSlot] != null) {
            spot[indexSlot] = null
            println("Slot $placeNumber is free.")
        } else println("There is no car in slot $placeNumber")

    }

    fun status(countSlots: Int) {
        var check = 0
        for (index in spot.indices) {
            if (spot[index] != null) {
                println("${index + 1} ${spot[index]?.reg_number} ${spot[index]?.color}")
            } else check++
        }
        if (check == countSlots) {
            println("Parking lot is empty.")
        }
    }

    fun findColor(color: String, command: String) {
        var check = 0
        val output = mutableListOf<String?>()
        for (index in spot.indices) {
            if (spot[index]?.color?.uppercase() == color.uppercase()) {
                if (command.startsWith("reg_by_color")) {
                    output.add(spot[index]?.reg_number)
                } else {
                    output.add((index+1).toString())
                }
            } else check++
        }
        if (check == countSlots) {
            println("No cars with color $color were found.")
        } else println(output.joinToString(", "))
    }

    fun findRegNumber(regNum: String) {
        var check = 0
        for (index in spot.indices) {
            if (spot[index]?.reg_number == regNum) {
                println("${index + 1}")
            } else check++
        }
        if (check == countSlots) {
            println("No cars with registration number $regNum were found.")
        }
    }
}

data class Car(val reg_number: String, val color: String)