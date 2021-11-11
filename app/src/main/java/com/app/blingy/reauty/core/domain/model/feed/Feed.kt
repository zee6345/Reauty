package com.app.blingy.reauty.core.domain.model.feed

/**
 * model to hold the data of Feed
 * @see [https://console.firebase.google.com/u/1/project/findme-57cf5/database/findme-57cf5/data/feed](Feed Node)
 * if [haveBrandMention] is [true] check [brand]
 * if [haveHashTagsMention] is [true] check [hashTags]
 * if [havePersonalMention] is [true] check [userMention]
 * if [haveProductMention] is [true] check [product]
 */
data class Feed(
    val comments: String = "",
    val haveBrandMention: Boolean = true,
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
    val post_status: String = "visible",
    val timeStamp: Long = 0L,
    val postOwnerImageLink: String = "",
   // val product: Map<String, Map<String, Product>> = emptyMap(),
   // val brand: Map<String, Map<String, Brand>> = emptyMap(),
   // val userMention: Map<String, Map<String, Map<String, String>>> = emptyMap(),
    //val hashTags: Map<String, Map<String, HashTags>> = emptyMap()
)

data class Product(
    val brandName: String = "",
    val id: String = "",
    val name: String = "",
    val productImage: String = "",
    val productLink: String = "",
    val productType: String = "",
    val productsName: String = ""
)

data class Brand(
    val id: String = "",
    val image: String = "",
    val name: String = ""
)

data class HashTags(
    val id: String = "",
    val isHashTag: Boolean = false,
    val name: String = "",
    val place: Place
)

data class Place(
    val length: Int = 0,
    val location: Int = 0
)