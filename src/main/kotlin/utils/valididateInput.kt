package utils

import utils.ScannerInput.readNextInt

object valididateInput {

    @JvmStatic
    fun readValidCount(prompt: String): Int {
        var input = readNextInt(prompt)
        do {
            if (Utilities.validRange(input, 1, 5000))
                return input
            else {
                print("You can follow up to 5000 accounts$input.")
                input = readNextInt(prompt)
            }
        } while (true)
    }

    @JvmStatic
    fun readValidCount2(prompt: String): Int {
        var input = readNextInt(prompt)
        do {
            if (Utilities.validRange(input, 1, 5000))
                return input
            else {
                print("You can be followed by up to 5000 accounts$input.")
                input = readNextInt(prompt)
            }
        } while (true)
    }

    @JvmStatic
    fun readValidCount3(prompt: String): Int {
        var input = readNextInt(prompt)
        do {
            if (Utilities.validRange(input, 1, 500))
                return input
            else {
                print("posts can have up to 500 likes$input.")
                input = readNextInt(prompt)
            }
        } while (true)
    }

    @JvmStatic
    fun readValidCount4(prompt: String): Int {
        var input = readNextInt(prompt)
        do {
            if (Utilities.validRange(input, 1, 500))
                return input
            else {
                print("posts can have up to 500 comments$input.")
                input = readNextInt(prompt)
            }
        } while (true)
    }
}
