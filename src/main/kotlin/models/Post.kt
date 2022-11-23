package models

data class Post (
    var postId:Int = 0,
    var photoName: String,
    var caption: String,
    var numOfLikes: Int,
    var numOfComments: Int
        )