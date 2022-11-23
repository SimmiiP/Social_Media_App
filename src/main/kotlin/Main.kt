import controllers.UserAPI
import models.User
import models.Post
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.ScannerInput.readNextChar
import kotlin.system.exitProcess

private val userAPI = UserAPI()
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
         > |   0) Exit                                         |
         > -----------------------------------------------------  
         > ==>>""".trimMargin(">")
)

//--------------------------------------------------------------------------------
// USER MENU
//--------------------------------------------------------------------------------
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
// POST MENU (only available for accounts that are Active Now
//----------------------------------------------------------------------------------------

private fun addPostToUser(){

}

fun deleteAPost() {

}