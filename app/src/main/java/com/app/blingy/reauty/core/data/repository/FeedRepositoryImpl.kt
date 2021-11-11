package com.app.blingy.reauty.core.data.repository

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.app.blingy.reauty.core.domain.model.feed.Feed
import com.app.blingy.reauty.core.domain.repository.FeedRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

/**
 * repository for Feed Node
 * @see [FeedRepository]
 * @see [https://console.firebase.google.com/u/1/project/findme-57cf5/database/findme-57cf5/data/feed](Feed Node)
 */
@ExperimentalCoroutinesApi
class FeedRepositoryImpl @ExperimentalCoroutinesApi
@Inject constructor(
    private val database: FirebaseDatabase
) : FeedRepository {
    override suspend fun getFeeds() = callbackFlow<Result<List<Feed>>> {
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Timber.e("onCancelled, ${error.message}")
                this@callbackFlow.trySend(Result.failure(error.toException())).isSuccess
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.map { ds ->
                    ds.getValue(Feed::class.java)
                }
                Timber.d("onDataChange, $items")
                this@callbackFlow.trySend(Result.success(items.filterNotNull()))
            }
        }
        database.getReference(RemoteConstant.feedsRef)
            .addValueEventListener(postListener)
        awaitClose {
            database.getReference(RemoteConstant.feedsRef)
                .removeEventListener(postListener)
        }
    }

}