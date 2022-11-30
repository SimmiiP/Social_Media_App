package controllers

import models.User
import persistance.Serializer
import utils.Utilities
import utils.Utilities.formatListString

class UserAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType

    private var users = ArrayList<User>()

    private var lastUserId = 0
    private fun getUserId() = lastUserId++

    fun addUser(user: User): Boolean {
        user.userId = getUserId()
        return users.add(user)
    }

    fun listAllUsers(): String =
        if (users.isEmpty())
            "No Users on the App"
        else
        formatListString(users)


    fun numberOfUsers() = users.size

    fun findUser(id: Int): User? {
        return users.find { user -> user.userId == id }
    }

    fun deleteUser(id: Int): Boolean {
        return users.removeIf { user -> user.userId == id }
    }

    fun updateUser(id: Int, newUser: User): Boolean {
        val foundUser = findUser(id)

        if (foundUser != null) {
            foundUser.fullName = newUser.fullName
            foundUser.userName = newUser.userName


            return true
        }

        return false
    }

    fun isValidIndex(index: Int): Boolean{
        return isValidListIndex(index, users);
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

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