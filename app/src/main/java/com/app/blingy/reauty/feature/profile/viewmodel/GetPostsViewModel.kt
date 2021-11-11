package com.app.blingy.reauty.feature.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.domain.model.post.Post
import com.app.blingy.reauty.feature.profile.usecasse.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GetPostsViewModel @Inject constructor(
  private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _postSizeLiveData = MutableLiveData<Int>()
    val postSizeLiveData : LiveData<Int>
        get() = _postSizeLiveData

    private val _postListLiveData = MutableLiveData<List<Post>>()
    val postListLiveData : LiveData<List<Post>>
        get() = _postListLiveData




    init {

        getPosts()

    }


    fun getPosts() = viewModelScope.launch {

        getPostUseCase().catch {
        }.collect {
         when{
             it.isSuccess ->{
                 val postSize = it.getOrNull()?.size

                 Timber.d("Post size is kdkdkd ${postSize}")
                 _postSizeLiveData.postValue(postSize!!)
                 val postList  = it.getOrNull()

                _postListLiveData.value = postList!!

             }
             it.isFailure ->{
                 Timber.d("Oh boy this thing don fail oo")
             }
         }
        }
    }
//        getPostService.getPosts()?.addOnSuccessListener { dataSnapshot ->
//            if (dataSnapshot.exists() && dataSnapshot !=null){
//
//                val postId = dataSnapshot.key
//
//                val post = dataSnapshot.child(postId!!).getValue(Post::class.java)
//
//                _postLiveData.postValue(post!!)
//
//
//            }else{
//                Timber.d("DataSnapsh is Empty")
//            }
//        }
//    }
}