package models

import utils.Utilities
 data class User(
     var userId: Int,
     var fullName: String,
     var userName: String,
     var activeNow: Boolean,
     var profilePicture: Boolean,
     var following: Int,
     var followers: Int,
     var verified: Boolean,
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

     fun deletePost(id: Int): Boolean {
         return posts.removeIf { post -> post.postId == id }
     }

     fun updatePost(id: Int, newPost: Post): Boolean {
         val foundPost = findOne(id)

         if (foundPost != null) {
             foundPost.photoName = newPost.photoName
             foundPost.caption = newPost.caption

             return true
         }

         return false
     }

     fun listAllPosts() =
         if (posts.isEmpty()) "\tNO RECENT POSTS"
         else Utilities.formatSetString(posts)



     /*override fun toString(): String {
         val active = if (userisActiveNow) 'Y' else 'N'
         return "$userId: $fullName, $userName, activeNow($active), $profilePicture, $following, $followers, \n${listPosts()}"
             }*/

 }
