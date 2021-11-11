package com.app.blingy.reauty.feature.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.data.remote.service.UserService
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.feature.profile.usecasse.GetUserUseCase
import com.app.blingy.reauty.feature.profile.usecasse.UploadUserProfilePictureUseCase
import com.google.firebase.storage.UploadTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
   private val getUserUseCase: GetUserUseCase,
    getUserService: UserService,
   private val uploadUserProfilePictureUseCase: UploadUserProfilePictureUseCase
): ViewModel() {

    private val _userDataLiveData = MutableLiveData<User>()

    val userDataLiveData: LiveData<User>
        get() = _userDataLiveData

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _showErrorSnackBar = MutableLiveData<Boolean>()
    val showErrorSnackBar: LiveData<Boolean>
        get() = _showErrorSnackBar



    var downloadUri : Uri? = Uri.EMPTY


    private val _userDataIsLoadingLiveData = MutableLiveData<Boolean>()
    val userDataIsLoading: LiveData<Boolean>
        get() = _userDataIsLoadingLiveData



    var uploadTask : UploadTask? = null

    val getUserService = getUserService

     fun uploadUserProfilePicture(data : ByteArray){
         uploadTask= uploadUserProfilePictureUseCase().putBytes(data)
         uploadTask?.addOnFailureListener{

             _showErrorSnackBar.postValue(true)

        }

         uploadTask?.addOnProgressListener { task ->
             val progress = (100 * task.bytesTransferred)/  task.totalByteCount
             if(progress < 100 ){
                 _loadingState.postValue(true)
             }else{
                 _loadingState.postValue(false)
             }
         }


         uploadTask?.addOnFailureListener{
             _showErrorSnackBar.postValue(true)
         }

         val urlTask = uploadTask?.continueWithTask { task ->
             if (!task.isSuccessful) {
                 task.exception?.let {
                     throw it
                 }
             }
             uploadUserProfilePictureUseCase().downloadUrl
         }?.addOnCompleteListener { task ->
             if (task.isSuccessful) {

                 _loadingState.postValue(false)

                  downloadUri = task.result

                 _showErrorSnackBar.postValue(false)


                 Timber.d("Your Download Uri is --- ${downloadUri}")
             } else {
                 _showErrorSnackBar.postValue(true)
             }
         }


    }




    init {
        getUserInViewModel()
    }

    fun getUserInViewModel() {
        viewModelScope.launch {
            getUserUseCase().catch {

            }.collect {
                when {
                    it.isSuccess -> {
                        val userData = it.getOrNull()
                        Timber.d("It was success and here is the user --> ${userData!!.userName}")
                        _userDataLiveData.postValue(userData!!)

                        _userDataIsLoadingLiveData.postValue(false)
                    }
                    it.isFailure -> {
                        it.exceptionOrNull()?.localizedMessage
                        _userDataIsLoadingLiveData.postValue(false)
                    }
                }
            }
        }
    }
}