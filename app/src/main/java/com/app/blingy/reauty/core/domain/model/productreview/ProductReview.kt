package com.app.blingy.reauty.core.domain.model.productreview

import com.app.blingy.reauty.core.domain.model.feed.Product

data class ProductReview(
   val concerns :List<Concerns> = emptyList(),
   val dateAdded: String = "",
   val goodPoints:String = "",
   val imageURLs :List<String> = emptyList(),
   val missingPoints: String = "",
   val numberOfLikes: Int = 0,
   val product : Product = Product(),
   val productID:String = "",
   val rating:Int = 0,
   val skinType:String = "",
   val uid :String = "",
   val userImageUrl:String = "",
   val username:String = ""
)
data class Concerns(
   val cencernPercentage : Long = 0L,
   val concernName:String = ""
)