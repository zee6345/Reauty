package com.app.blingy.reauty.core.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.blingy.reauty.core.data.JsonReader
import com.app.blingy.reauty.core.domain.model.user.User
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class UserRepositoryImplTest {

//    private val moshi = Moshi.Builder().build()
//    private val type = Types.newParameterizedType(List::class.java, User::class.java)
//    private val adapter = moshi.adapter<List<User>>(type)

    var moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    //var adapter: JsonAdapter<List<User>> = moshi.adapter<List<User>>(User::class.java)

    val listType = Types.newParameterizedType(List::class.java, User::class.java)
    val adapter: JsonAdapter<List<User>> = moshi.adapter(listType)


    private val defaultUserCount = 16
    private val firstUserName = "namaei"
    private val firstUserSearchName = "searchme"
    private val firstUserId = "1Ui8XxacaiWAVFNvIqHPgzP9xjq1"

//    @Before
//    fun setup(){
//        moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        adapter = moshi.adapter<List<User>>(User::class.java)
//    }

    @Test
    fun test_user_count_first_user_name_and_unique_name_return_true() {
        val jsonString = JsonReader.readFileWithNewLineFromResources("test_user.json")
        println(jsonString)
        println("=")
        val users = adapter.fromJson(jsonString)
        println(users)
        println("=")
        assertThat(users?.size).isEqualTo(defaultUserCount)
        assertThat(users?.get(0)?.userName).isEqualTo(firstUserName)
        assertThat(users?.get(0)?.userSearchName).isEqualTo(firstUserSearchName)
    }

    @Test
    fun test_user_search_name_return_true() {
        val jsonString = JsonReader.readFileWithNewLineFromResources("test_user.json")
        println(jsonString)
        println("=")
        val users = adapter.fromJson(jsonString)
        println(users)
//        val user = users.forEach {
//            val foundUser = it.userSearchName == firstUserSearchName
//        }
        val model = users?.find { it.userSearchName == firstUserSearchName }
        assertThat(model?.userName).isEqualTo(firstUserName)
    }

}