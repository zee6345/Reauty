package com.app.blingy.reauty.core.data.repository

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.app.blingy.reauty.core.domain.model.post.Post
import com.app.blingy.reauty.core.domain.repository.PostRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PostRepositoryImpl @ExperimentalCoroutinesApi
@Inject constructor(
    private val database : FirebaseDatabase
): PostRepository {
    override suspend fun getPosts() =  callbackFlow<Result<List<Post>>> {
            val postListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) { this@callbackFlow.trySend(Result.failure(error.toException())).isSuccess
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val items = dataSnapshot.children.map { ds ->
                        ds.getValue(Post::class.java)
                    }
                    this@callbackFlow.trySend(Result.success(items.filterNotNull()))
                }
            }
            database.getReference(RemoteConstant.posts).child("DTP7x2qGBfYaiM4KYEX7kWJHdyr1")
                .addValueEventListener(postListener)
            awaitClose {
                database.getReference(RemoteConstant.posts).child("DTP7x2qGBfYaiM4KYEX7kWJHdyr1")
                    .removeEventListener(postListener)
            }
        }

    }
