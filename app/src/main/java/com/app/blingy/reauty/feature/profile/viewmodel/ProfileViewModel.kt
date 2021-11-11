package com.app.blingy.reauty.feature.profile.viewmodel

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.app.blingy.reauty.feature.profile.repository.Repository
//import com.app.blingy.reauty.feature.profile.viewstate.Response
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class ProfileViewModel @Inject constructor(
//   val repository: Repository
//) : ViewModel(){
//
//
//
//    fun getResponseUsingCallback(callback: FirebaseCallback) = viewModelScope.launch {
//        repository.getResponseFromRealtimeDatabaseUsingCallback(callback)
//    }

//    fun getResponseUsingLiveData() : LiveData<Response> {
//        return repository.getResponseFromRealtimeDatabaseUsingLiveData()
//    }




//    fun getUSer() = viewModelScope.launch {
//        val checkUser = repository.getDatabaseReference.child("YyN3cSsQGrekXnWB7b4JG6W3vso2").addListenerForSingleValueEvent(
//            object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()){
//                        val user = snapshot.getValue<User>()
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            }
//    }
//



//}