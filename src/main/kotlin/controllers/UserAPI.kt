package controllers

import models.Post
import models.User
import persistance.Serializer
import utils.Utilities
import utils.Utilities.formatListString
import utils.Utilities.isValidListId

class UserAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType

    private var users = ArrayList<User>()

    private var lastUserId = 0


    private fun getUserId() = lastUserId++

    // FIND USERS
    fun findUser(id: Int): User? {
        return users.find { user -> user.userId == id }
    }


    //ADD USERS
    fun addUser(user: User): Boolean {
        user.userId = getUserId()
        return users.add(user)
    }

    //LIST USERS
    fun listAllUsers(): String =
        if (users.isEmpty())
            "No Users on the App"
        else
        formatListString(users)

    fun listActiveUsers() =
        if (numberOfActiveUsers() == 0) "No users online"
        else formatListString(users.filter { user -> user.activeNow == true })

    fun listOfflineUsers() =
        if (numberOfUsersOffline() == 0) "All users are online"
        else formatListString(users.filter { user -> user.activeNow == false })

    fun listUsersWithProfilePic() =
        if (numberOfUsersWithProfilePic() == 0) "No users have a profile pic"
        else formatListString(users.filter { user -> user.profilePicture == true })

    fun listUsersWithoutProfilePic() =
        if (numberOfUsersWithProfilePic() == 0) "All users have a profile pic"
        else formatListString(users.filter { user -> user.profilePicture == false })

    fun listVerifiedUsers() =
        if (numberOfVerifiedUsers() == 0) "No users are verified"
        else formatListString(users.filter { user -> user.verified == true })

    fun listUnverifiedUsers() =
        if (numberOfVerifiedUsers() == 0) "All users are verified"
        else formatListString(users.filter { user -> user.verified == false })

    //COUNT USERS

    fun numberOfUsers() = users.size
    fun numberOfActiveUsers(): Int = users.count { user: User -> user.activeNow }
    fun numberOfUsersOffline(): Int = users.count { user: User -> !user.activeNow }

    fun numberOfUsersWithProfilePic(): Int = users.count { user: User -> user.profilePicture }
    fun numberOfUsersWithoutProfilePic(): Int = users.count { user: User -> !user.profilePicture }

    fun numberOfVerifiedUsers(): Int = users.count { user: User -> user.verified }
    fun numberOfUnverifiedUsers(): Int = users.count { user: User -> !user.verified }

    //UPDATE USERS

    fun updateUser(id: Int, user: User): Boolean {
        val foundUser = findUser(id)

        if (foundUser != null) {
            foundUser.fullName = user.fullName
            foundUser.userName = user.userName
            foundUser.activeNow = user.activeNow
            foundUser.profilePicture = user.profilePicture
            foundUser.following = user.following
            foundUser.followers = user.followers
            foundUser.verified = user.verified


            return true
        }

        return false
    }

    //DELETE USERS
    fun deleteUser(id: Int): Boolean {
        return users.removeIf { user -> user.userId == id }
    }

    //ACTIVE AND VERIFIED USERS FUNCTIONS

    fun goOnline(id: Int): Boolean {
        val foundUser = findUser(id)

        if ((foundUser !=null) && !foundUser.activeNow) {
            foundUser.activeNow = true
            return true
        }
        return false
    }

    fun goOffline(id: Int): Boolean {
        val foundUser = findUser(id)

        if ((foundUser !=null) && foundUser.activeNow) {
            foundUser.activeNow = false
            return true
        }
        return false
    }

    fun getVerified(id: Int): Boolean {
        val foundUser = findUser(id)

        if ((foundUser !=null) && !foundUser.verified) {
            foundUser.verified = true
            return true
        }
        return false
    }

    fun unverifyAccount(id: Int): Boolean {
        val foundUser = findUser(id)

        if ((foundUser !=null) && foundUser.verified) {
            foundUser.verified = false
            return true
        }
        return false
    }

    fun profilePic(id: Int): Boolean {
        val foundUser = findUser(id)

        if ((foundUser !=null) && !foundUser.profilePicture) {
            foundUser.profilePicture = true
            return true
        }
        return false
    }

    fun noProfilePic(id: Int): Boolean {
        val foundUser = findUser(id)

        if ((foundUser !=null) && foundUser.profilePicture) {
            foundUser.profilePicture = false
            return true
        }
        return false
    }

    // VALIDATION

    fun isValidId(id: Int): Boolean{
        return isValidListId(id, users);
    }


    //SEARCH

    fun searchByUsername(searchString: String) =
        formatListString(
            users.filter {user -> user.userName.contains(searchString, ignoreCase = true)} )

    fun searchByFullName(searchString: String) =
        formatListString(
            users.filter {user -> user.fullName.contains(searchString, ignoreCase = true)} )



    //SAVE AND LOAD

    @Throws(Exception::class)
    fun load() {
        users = serializer.read() as ArrayList<User>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(users)
    }

}