package com.app.blingy.reauty.core.domain.model.post

data class Post(
    val comments: String = "",
    val haveHashTagsMention: Boolean = false,
    val havePersonalMention: Boolean = false,
    val haveProductMention: Boolean = false,
    val likes: Int = 0,
    val postImageLink: String = "",
    val postOwnerID: String = "",
    val postOwnerName: String = "",
    val postText: String = "",
    val postType: String = "",
    val postVideoLink: String = "",
    val post_status: String = "",
    val timeStamp: Long = 0L,
    val userProfileImage: String  = ""
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "comments" to comments,
            "haveHashTagsMention" to haveHashTagsMention,
            "havePersonalMention" to havePersonalMention,
            "likes" to likes,
            "postImageLink" to postImageLink,
            "postOwnerID" to postOwnerID,
            "postOwnerName" to postOwnerName,
            "postText" to postText,
            "postType" to postType,
            "postVideoLink" to postVideoLink,
            "post_status" to post_status,
            "userProfileImage" to userProfileImage
//            "timeStamp" to timeStamp
        )
    }
}


object DataSource {

    val ratingList: List<Post>
        get() {
            val post = ArrayList<Post>()
            post.add(Post(postImageLink = "https://firebasestorage.googleapis.com:443/v0/b/findme-57cf5.appspot.com/o/postsImages%2F4A34E866-E6F3-44BB-B572-A20353A70956.jpg?alt=media&token=125585d6-b51c-4d2d-acaa-4642b1a99539"))
            post.add(Post(postImageLink = "https://firebasestorage.googleapis.com:443/v0/b/findme-57cf5.appspot.com/o/postsImages%2F4A34E866-E6F3-44BB-B572-A20353A70956.jpg?alt=media&token=125585d6-b51c-4d2d-acaa-4642b1a99539"))
            post.add(Post(postImageLink = "https://firebasestorage.googleapis.com:443/v0/b/findme-57cf5.appspot.com/o/postsImages%2F4A34E866-E6F3-44BB-B572-A20353A70956.jpg?alt=media&token=125585d6-b51c-4d2d-acaa-4642b1a99539"))
            return post
        }
}
