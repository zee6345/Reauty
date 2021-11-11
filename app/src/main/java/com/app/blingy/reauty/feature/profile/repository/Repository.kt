package com.app.blingy.reauty.feature.profile.repository

import androidx.lifecycle.MutableLiveData
import com.app.blingy.reauty.core.data.remote.service.UserService
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.feature.profile.viewmodel.FirebaseCallback
import com.app.blingy.reauty.feature.profile.viewstate.Response
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject
import java.lang.Exception
import java.util.*


//class Repository @Inject constructor(
//   val refrence : FirebaseDatabase
//) {
//    val ref = refrence.getReference(RemoteConstant.usersRef).child("YyN3cSsQGrekXnWB7b4JG6W3vso2")
//     fun getResponseFromRealtimeDatabaseUsingCallback(callback: FirebaseCallback) {
//        ref.get().addOnCompleteListener { task ->
//            val response = Response()
//            if (task.isSuccessful) {
//                val result = task.result
//                result?.let {datasnapshot ->
//                    if (datasnapshot.exists()){
//                        response.user?.userName = result.value.toString()
//
//                    }
//
//                }
//            } else {
//                response.exception = task.exception
//            }
//            callback.onResponse(response)
//        }
//    }
//    fun getResponseFromRealtimeDatabaseUsingLiveData() : MutableLiveData<Response> {
//        val mutableLiveData = MutableLiveData<Response>()
//        ref.get().addOnCompleteListener { task ->
//            val response = Response()
//            if (task.isSuccessful) {
//                val result = task.result
//                result?.let {
//                    response.user = result.getValue(User::class.java)
//                }
//            } else {
//                response.exception = task.exception
//            }
//            mutableLiveData.value = response
//        }
//        return mutableLiveData
//    }
//}

