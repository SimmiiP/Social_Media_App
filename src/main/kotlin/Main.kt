import controllers.UserAPI
import models.User
import models.Post
import persistance.XMLSerializer
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.ScannerInput.readNextChar
import java.io.File
import kotlin.system.exitProcess

private val userAPI = UserAPI(XMLSerializer(File("users.xml")))
fun main() = runMenu()

fun runMenu(){
    do{
        when (val option = mainMenu()){
            1 -> addUser()
            2 -> listUsers()
            3 -> updateUser()
            4 -> deleteUser()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun mainMenu() = readNextInt(
    """
         > -----------------------------------------------------  
         > |                  NOTE KEEPER APP                  |
         > -----------------------------------------------------  
         > | NOTE MENU                                         |
         > |   1) Make an account                              |
         > |   2) List accounts                                |
         > |   3) Update your account                          |
         > |   4) Delete your account                          |
         > ----------------------------------------------------- 
         >    20) Save Account                                 |
         >    21) Load Accounts                                |
         > -----------------------------------------------------
         > |   0) Exit                                         |
         > -----------------------------------------------------  
         > ==>>""".trimMargin(">")
)

//--------------------------------------------------------------------------------
// USER MENU
//--------------------------------------------------------------------------------

// SAVE AND LOAD
fun saveNotes(){
    try {
        userAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun loadNotes(){
    try {
        userAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading to file: $e")
    }
}

fun addUser(){

}

fun listUsers(){

}

fun deleteUser(){

}

fun updateUser(){

}

fun exitApp(){

}

//----------------------------------------------------------------------------------------
// POST MENU (only available for accounts that are Active Now.
//----------------------------------------------------------------------------------------

private fun addPostToUser(){

}

fun deleteAPost() {

}