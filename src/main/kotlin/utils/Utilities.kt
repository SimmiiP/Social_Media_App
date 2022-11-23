package utils

import models.Post
import models.Account

object Utilities {

    // NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun formatListString(accountsToFormat: List<Account>): String =
        accountsToFormat
            .joinToString(separator = "\n") { account ->  "$account" }

    @JvmStatic
    fun formatSetString(postsToFormat: Set<Post>): String =
        postsToFormat
            .joinToString(separator = "\n") { post ->  "\t$post" }

}