package controllers

import models.User
import utils.Utilities

class UserAPI() {
    private var users = ArrayList<User>()

        private var lastUserId = 0
        private fun getUserId() = lastUserId++

        fun addUser(user: User): Boolean {
            user.userId = getUserId()
            return users.add(user)
        }

        fun numberOfUsers() = users.size

        fun findOne(id: Int): User? {
            return users.find { user -> user.userId == id }
        }

        fun deleteUser(id: Int): Boolean {
            return users.removeIf { user -> user.userId == id }
        }

        fun updateUser(id: Int, newUser: User): Boolean {
            val foundUser = findOne(id)

            if (foundUser != null) {
                foundUser.fullName = newUser.fullName
                foundUser.userName = newUser.userName


                return true
            }

            return false
        }


        fun listAllUsers() =
            if (users.isEmpty()) "\tNO USERS"
            else Utilities.formatListString(users)
}