package models

import utils.Utilities
 data class Account(
     var userID: Int,
     var fullName: String,
     var userName: String,
     var activeNow: Boolean,
     var profilePicture: Boolean,
     var following: Int,
     var followers: Int,
     var posts : MutableSet<Post> = mutableSetOf()
 ) {
     private var lastPostId = 0
     private fun getPostId() = lastPostId++

     fun addPost(post: Post): Boolean {
         post.postId = getPostId()
         return posts.add(post)
     }

     fun numberOfPosts() = posts.size

     fun findOne(id: Int): Post? {
         return posts.find { post -> post.postId == id }
     }

     fun delete(id: Int): Boolean {
         return posts.removeIf { post -> post.postId == id }
     }

     fun update(id: Int, newPost: Post): Boolean {
         val foundPost = findOne(id)

         if (foundPost != null) {
             foundPost.caption = newPost.caption

             return true
         }

         return false
     }

     fun listPosts() =
         if (posts.isEmpty()) "\tNO RECENT POSTS"
         else Utilities.formatSetString(posts)
 }

