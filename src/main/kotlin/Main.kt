import controllers.UserAPI
import models.User
import models.Post
import mu.KotlinLogging
import persistance.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.ScannerInput.readNextChar
import utils.ScannerInput.readNextPhotoName
import java.io.File
import kotlin.system.exitProcess
import utils.photoNameUtility
import utils.valididateInput
import utils.valididateInput.readValidCount
import utils.valididateInput.readValidCount2
import utils.valididateInput.readValidCount3
import utils.valididateInput.readValidCount4

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"

private val logger = KotlinLogging.logger({})
private val userAPI = UserAPI(XMLSerializer(File("users.xml")))
fun main(args: Array<String>){
    runMenu()
}

// MENUS
fun mainMenu() = readNextInt(
    """
         -----------------------------------------------------  
         |                  Social Media App                 |
         -----------------------------------------------------  
         |                     USER MENU                     |
         -----------------------------------------------------
         |   1) Make an account                              |
         |   2) List accounts                                |
         |   3) Update your account                          |
         |   4) Delete your account                          |
         |   5) Search accounts
         |   6) Posts                                        |
         ----------------------------------------------------- 
         |   20) Save Account                                |
         |  21) Load Accounts                                |
         -----------------------------------------------------
         ${ANSI_RED}|   0) Exit                                         |
         -----------------------------------------------------  
         ==>>${ANSI_RESET}""".trimIndent()
)

fun subMenu(): Int {
    return ScannerInput.readNextInt("""${ANSI_CYAN}
       -------------------------------
       |      Social Media App       |
       -------------------------------
       |       POSTS SUBMENU         |
       -------------------------------
       |   1) Add a post             |
       |   2) View recent posts      |
       |   3) Edit a post            | 
       |   5) Delete a post          |
       |                             |
       |                             |
       -------------------------------
       ${ANSI_RED}|   0) Exit SubMenu           |
       -------------------------------  
    ==>> ${ANSI_RESET}""".trimIndent())
}

fun subMenu2(): Int {
    return ScannerInput.readNextInt(
        """${ANSI_PURPLE}
           ------------------------------------------------
           |             Social Media App                 |
           ------------------------------------------------
           |            LIST USERS SUBMENU                |
           ------------------------------------------------
           |   1) List All Users                          |
           |   2) List Active Users                       |
           |   3) List Offline Users                      |
           |   4) List Users with a Profile Picture       |
           |   5) List Users without a Profile Picture    |
           |   6) List Verified Users that are Active Now |
           |   7) List Unverified Users                   |
           ------------------------------------------------
           ${ANSI_RED}|   0) Exit SubMenu                            |
           ------------------------------------------------
        ==>> ${ANSI_RESET}""".trimIndent()
    )
}

fun subMenu4(): Int {
    return ScannerInput.readNextInt(
        """${ANSI_BLUE}
           ---------------------------------------------------
           |                 Social Media App                |
           ---------------------------------------------------
           |                LIST POSTS SUBMENU               |
           ---------------------------------------------------
           |   1) List All a Users Posts                     |
           |   2) List Posts by Verified Users who are Active|
           |   3) List Offline Users                         |
           ---------------------------------------------------
           ${ANSI_RED}|   0) Exit SubMenu                         |
           ---------------------------------------------------
        ==>> ${ANSI_RESET}""".trimIndent()
    )
}

fun subMenu3(): Int {
    return ScannerInput.readNextInt(
        """${ANSI_GREEN}
           ------------------------------------------------
           |             Social Media App                 |
           ------------------------------------------------
           |           SEARCH USERS SUBMENU               |
           ------------------------------------------------
           |   1) Search users by full name               |
           |   2) Search by username                      |
           ------------------------------------------------
           ${ANSI_RED}|   0) Exit SubMenu                            |
           ------------------------------------------------
        ==>> ${ANSI_RESET}""".trimIndent()
    )
}

//RUN MENUS AND SUBMENUS
fun runMenu(): Int {
    do{
        when (val option = mainMenu()){
            1 -> addUser()
            2 -> runSubMenu2()
            3 -> updateUser()
            4 -> deleteUser()
            5 -> runSubMenu3()
            6 -> runSubMenu()
            20 -> saveUsers()
            21 -> loadUsers()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun runSubMenu(){
    do{
        when (val option = subMenu()){
            1 -> addPostToUser()
            2 -> runSubMenu4()
            3 -> updatePostsContentsInUser()
            4 -> deleteAPost()
            0 -> exitSubMenu()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)

}

fun runSubMenu2(){
    do{
        when (val option = subMenu2()){
            1 -> listAllUsers()
            2 -> listActiveUsers()
            3 -> listOfflineUsers()
            4 -> listUsersWithProfilePic()
            5 -> listUsersWithoutProfilePic()
            6 -> listVerifiedUsers()
            7 -> listUnverifiedUsers()
            0 -> exitSubMenu()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)

}

fun runSubMenu4(){
    do{
        when (val option = subMenu4()){
            1 -> listAllAUsersPosts()
            2 -> listVerifiedUsersPosts()
            3 -> listOfflineUsersPosts()
            0 -> exitSubMenu()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)

}

fun runSubMenu3(){
    do{
        when (val option = subMenu3()){
            1 -> searchForUsersByFullName()
            2 -> searchByUsername()
            0 -> exitSubMenu()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)

}

/*fun runSubMenu5(){
    do{
        when (val option = subMenu5()){
            1 -> searchForPostsByPhotoName()
            2 -> searchByCaption()
            0 -> exitSubMenu()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)

}*/

// SAVE AND LOAD
fun saveUsers(){
    try {
        userAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun loadUsers(){
    try {
        userAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading to file: $e")
    }
}

// ADD

// ADD USERS
fun addUser(){
logger.info {"addUser() function invoked"}

    val fullName = readNextLine("Enter your full name please")
    val userName = readNextLine("Create your username")
    val activeNow = readNextChar("Set account to active by inputting 'y' or inactive by inputting 'n'")
    val booleanActiveNow = (activeNow == 'y'|| activeNow == 'Y')
    val profilePicture = readNextChar("Do you want to set a profile picture? 'y' or 'n'")
    val booleanProfilePicture = (profilePicture == 'y' || profilePicture == 'Y')
    val following = readValidCount("How many accounts do you follow? (Up to 5000)")
    val followers = readValidCount2("How many accounts are following you? (Up to 5000)")
    val verified = readNextChar("You can apply for verification! Input 'y' to apply! ")
    val booleanVerified = (verified == 'y' || verified == 'Y')
    val isAdded = userAPI.addUser(User(
        userId = 0, fullName = fullName, userName = userName, activeNow = booleanActiveNow, profilePicture = booleanProfilePicture,
        following = following, followers = followers, verified = booleanVerified ))

    if(isAdded){
        println("Added Successfully")
    }else{
        println("Add Failed")
    }
}

//ADD POSTS
private fun addPostToUser(){
    logger.info {"addPost() function invoked"}
    listActiveUsers()
    val user: User? = askUserToChooseActiveAccount()
    if (user != null){
    userAPI.findUser(0)!!.addPost(
        Post(
            postId = 0,
             photoName = readNextPhotoName("\t Pick a name for this post from: ${photoNameUtility.photoNames}"),
             caption = readNextLine("\t Add a caption!"),
             numOfLikes = readValidCount3("How many likes does it have?"),
             numOfComments = readValidCount4("How many comments does it have?")
        )
    )
}}

//LIST

//LIST USERS
fun listAllUsers(){
    logger.info {"listAllUsers() function invoked"}
    println(userAPI.listAllUsers())
}

fun listActiveUsers(){
    logger.info {"listActiveUsers() function invoked"}
    println(userAPI.listActiveUsers())
}

fun listOfflineUsers(){
    logger.info {"listOfflineUsers() function invoked"}
    println(userAPI.listOfflineUsers())
}

fun listUsersWithProfilePic(){
    logger.info {"listUsersWithProfilePic() function invoked"}
    println(userAPI.listUsersWithProfilePic())
}

fun listUsersWithoutProfilePic(){
    logger.info {"listUsersWithoutProfilePic() function invoked"}
    println(userAPI.listUsersWithoutProfilePic())
}

fun listVerifiedUsers(){
    logger.info {"listVerifiedUsers() function invoked"}
    println(userAPI.listVerifiedUsers())
}

fun listUnverifiedUsers(){
    logger.info {"listUnverifiedUsers() function invoked"}
    println(userAPI.listUnverifiedUsers())
}

//LIST POSTS
fun listAllAUsersPosts(){
    val user: User? = askUserToChooseActiveAccount()
    if(user!=null){
        println(user.listAllPosts())
    }
}

fun listVerifiedUsersPosts(){
    val user: User? = askUserToChooseVerifiedAccount()
    if(user!= null){
        println(user.listAllPosts())
    }else{
        println("This User is not Verified")
    }
}

fun listOfflineUsersPosts(){
    val user: User? = askUserToChooseOfflineAccount()
    if(user!= null){
        println(user.listAllPosts())
    }else{
        println("This User is Active Now")
    }
}
//UPDATE

//UPDATE USER
fun updateUser(){
    logger.info {"updateNote() function invoked"}
    listAllUsers()

    if (userAPI.numberOfUsers() > 0) {
        val id = readNextInt("Enter the id of user profile you want to update: ")
        if (userAPI.findUser(id) != null) {
            val fullName = readNextLine("Enter your full name ")
            val userName = readNextLine("Enter your new user name ")
            val activeNow = readNextChar("Set account to active by inputting 'y' or inactive by inputting 'n'")
            val booleanActiveNow = (activeNow == 'y'|| activeNow == 'Y')
            val profilePicture = readNextChar("Do you want to set a profile picture? 'y' or 'n'")
            val booleanProfilePicture = (profilePicture == 'y' || profilePicture == 'Y')
            val following = readNextInt("How many accounts do you follow?")
            val followers = readNextInt("How many accounts are following you?")
            val verified = readNextChar("You can apply for verification! Input 'y' to apply! ")
            val booleanVerified = (verified == 'y' || verified == 'Y')
            if (userAPI.updateUser(id, User(0, fullName = fullName, userName = userName, activeNow = booleanActiveNow, profilePicture = booleanProfilePicture, following = following, followers = followers, verified = booleanVerified ))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("This user doesn't exist")
        }
    }
}

//UPDATE POSTS

fun updatePostsContentsInUser() {
    val user: User? = askUserToChooseActiveAccount()
    if (user != null) {
        val post: Post? = askUserToChoosePost(user)
        if (post != null) {
            val newPhotoName = readNextLine("Rename this post: ")
            val newCaption = readNextLine("Write a new caption")
            val newLikeNums = readNextInt("Change Likes")
            val newCommentNums = readNextInt("Change Comments")
            if (user.updatePost(post.postId, Post(photoName = newPhotoName, caption = newCaption, numOfLikes = newLikeNums, numOfComments = newCommentNums )))
            {
                println("Item contents updated")
            } else {
                println("Item contents NOT updated")
            }
        } else {
            println("Invalid Item Id")
        }
    }
}

//DELETE
fun deleteUser(){
    listAllUsers()
    if (userAPI.numberOfUsers() > 0) {
        // only ask the user to choose the account to delete if profile exists
        val id = readNextInt("Enter the id of the user to delete: ")
        // pass the index of the profile to userAPI for deleting and check for success.
        val userToDelete = userAPI.deleteUser(id)
        if (userToDelete) {
            println("Delete Successful!")
        } else {
            println("Delete NOT Successful")
        }
    }
}

//DELETE POSTS
fun deleteAPost() {
    val user: User? = askUserToChooseActiveAccount()
    if (user != null){
        val post: Post? = askUserToChoosePost(user)
        if (post != null){
            val isDeleted = user.deletePost(post.postId)
            if (isDeleted){
                println("Delete Successful")
            } else{
                println("Delete NOT Successful")
            }
        }
    }

}

//SEARCH
fun searchByUsername(){
    val searchUsername = readNextLine( "Enter the username to search by: ")
    val searchResults = userAPI.searchByUsername(searchUsername)
    if (searchResults.isEmpty()){
        println("This user doesn't exist")
    } else {
        println(searchResults)
    }
}

fun searchForUsersByFullName(){
    val searchFullName = readNextLine( "Enter the name to search by: ")
    val searchResults = userAPI.searchByFullName(searchFullName)
    if (searchResults.isEmpty()){
        println("This user doesn't exist")
    } else {
        println(searchResults)
    }
}

//EXITS
fun exitApp() {
    println("Exiting...bye")
    exitProcess(0)
}

fun exitSubMenu(){
    println("Exiting SubMenu byeeeeeee")
    System.exit(runMenu())
}


//------------------------------------
//HELPER FUNCTIONS
//------------------------------------

private fun askUserToChooseActiveAccount(): User? {
    listActiveUsers()
    if (userAPI.listActiveUsers() > 0.toString()) {
        val user = userAPI.findUser(readNextInt("\nEnter the id of the account you'd like to access: "))
        if (user != null) {
            if (!user.activeNow) {
                println("This user is NOT online")
            } else {
                return user //chosen user is active
            }
        } else {
            println("This user does not exist")
        }
    }
    return null //selected user is not active
}

private fun askUserToChooseOfflineAccount(): User? {
    listOfflineUsers()
    if (userAPI.listOfflineUsers() > 0.toString()) {
        val user = userAPI.findUser(readNextInt("\nEnter the id of the account you'd like to access: "))
        if (user != null) {
            if (user.activeNow) {
                println("This user is active now")
            } else {
                return user //chosen user is offline
            }
        } else {
            println("This user does not exist")
        }
    }
    return null //selected user is active
}

private fun askUserToChooseVerifiedAccount(): User? {
    listAllUsers()
    if (userAPI.listVerifiedUsers() > 0.toString()) {
        val user = userAPI.findUser(readNextInt("\nEnter the id of the account you'd like to access: "))
        if (user != null) {
            if (!user.verified) {
                println("This user is NOT verified")
            } else {
                return user //chosen user is verified
            }
        } else {
            println("This user does not exist")
        }
    }
    return null //selected user is not verified
}

private fun askUserToChoosePost(user: User): Post? {
    if (user.numberOfPosts() > 0) {
        print(user.listAllPosts())
        return user.findOne(readNextInt("\nEnter the id of the post to find: "))
    }
    else{
        println ("This user hasn't posted anything")
        return null
    }
}

