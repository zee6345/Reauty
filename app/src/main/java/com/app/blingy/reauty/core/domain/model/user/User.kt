package com.app.blingy.reauty.core.domain.model.user

import com.google.firebase.database.Exclude
import com.google.firebase.database.ServerValue
import com.google.firebase.database.core.ServerValues
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.io.Serializable
import java.sql.Timestamp

/**
 * model to hold the data of Beauty Tip User
 * @see [https://console.firebase.google.com/u/1/project/findme-57cf5/database/findme-57cf5/data](Beauty Tips db)
 */
data class User(
    val age: String = "",
    val bioText: String = "",
    val coins: Int = 0,
    val createdAt: Long = 0L,
    val firstName: String = "",
    val followersCount: Int = 0,
    val followingsCount: Int = 0,
    val gender: String = "",
    val lastModified: Long = 0L,
    val lastName: String = "",
    val loginType: String = "",
    val loginTypeDevice: String = "Android",
    val profilePicLink: String = "",
    val skintype: String = "",
    val totalNumberOfReviews: Int = 0,
    val typeOfUser: String = "",
    val uid: String = "",
    val userName: String = "",
    val userSearchName: String = "",
    val skinCareAbout: List<String> = emptyList(),
    val skinConcer: List<String> = emptyList(),
    val notificationsToken: Map<String, Boolean>? = null,
):Serializable {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "firstName" to firstName,
            "lastName" to lastName,
            "age" to age,
            "bioText" to bioText,
            "gender" to gender,
            "loginType" to loginType,
            "loginTypeDevice" to loginTypeDevice,
            "createdAt" to createdAt,
            "lastModified" to lastModified,
            "typeOfUser" to typeOfUser,
            "profilePicLink" to profilePicLink,
            "skintype" to skintype,
            "userSearchName" to userSearchName,
            "userName" to userName
        )
    }
}
