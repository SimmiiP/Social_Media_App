package utils

import models.Post
import models.User

object Utilities {

    // NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun formatListString(usersToFormat: List<User>): String =
        usersToFormat
            .joinToString(separator = "\n") { user ->  "$user" }

    @JvmStatic
    fun formatSetString(postsToFormat: Set<Post>): String =
        postsToFormat
            .joinToString(separator = "\n") { post ->  "\t$post" }

    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean{
        return numberToCheck in min .. max
    }

}