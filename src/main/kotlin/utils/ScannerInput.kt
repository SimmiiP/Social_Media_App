package utils

import java.lang.NumberFormatException
import java.util.*

object ScannerInput {

    @JvmStatic
    fun readNextInt(prompt: String): Int {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    @JvmStatic
    fun readNextLine(prompt: String?): String {
        print(prompt)
        return Scanner(System.`in`).nextLine()
    }


    @JvmStatic
    fun readNextChar(prompt: String?): Char {
        print(prompt)
        return Scanner(System.`in`).next()[0]
    }

    @JvmStatic
    fun readNextPhotoName(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do{
            if (photoNameUtility.isValidPhotoName(input))
                return input
            else{
                print("Invalid name $input. Please pick from the list")
                input = Scanner(System. `in`).nextLine()
            }
        } while (true)
    }

}