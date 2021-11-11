package com.app.blingy.reauty.feature.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * model to hold the data of feed
 */
@Parcelize
data class UiModelFeed(
    val comments: String = "",
    val haveBrandMention: Boolean = true,
    val haveHashTagsMention: Boolean = false,
    val havePersonalMention: Boolean = false,
    var likes: Int = 0,
    val postImageLink: String = "",
    val postOwnerId: String = "",
    val postOwnerName: String = "",
    val postText: String = "",
    val postType: String = "",
    val postVideoLink: String = "",
    val postStatus: String = "visible",
    val timeStamp: Long = 0L,
    val postOwnerImageLink: String = "",
    var isExpand: Boolean = false,
    var isFavourite: Boolean = false,
   // val product: Map<String, Map<String, Product>> = emptyMap(),
  //  val brand: Map<String, Map<String, Brand>> = emptyMap(),
   // val userMention: Map<String, Map<String, Map<String, String>>> = emptyMap(),
    //val hashTags: Map<String, Map<String, HashTags>> = emptyMap()
) : Parcelable

@Parcelize
data class Product(
    val brandName: String = "",
    val id: String = "",
    val name: String = "",
    val productImage: String = "",
    val productLink: String = "",
    val productType: String = "",
    val productsName: String = ""
) : Parcelable

@Parcelize
data class Brand(
    val id: String = "",
    val image: String = "",
    val name: String = ""
) : Parcelable

@Parcelize
data class HashTags(
    val id: String = "",
    val isHashTag: Boolean = false,
    val name: String = "",
    val place: Place
) : Parcelable

@Parcelize
data class Place(
    val length: Int = 0,
    val location: Int = 0
) : Parcelable