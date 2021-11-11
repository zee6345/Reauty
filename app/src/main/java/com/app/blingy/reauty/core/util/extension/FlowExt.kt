package com.app.blingy.reauty.core.util.extension

import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn

@ExperimentalCoroutinesApi
fun DatabaseReference.observeValue(): Flow<DataSnapshot?> =
    callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                this@callbackFlow.trySend(snapshot).isSuccess
            }
        }
        addValueEventListener(listener)
        awaitClose { removeEventListener(listener) }
    }

@ExperimentalCoroutinesApi
fun View.clicks(): Flow<Unit> = callbackFlow {
    val listener = View.OnClickListener { this.trySend(Unit).isSuccess }
    setOnClickListener(listener)
    awaitClose { setOnClickListener(null) }
}

fun <T> Flow<T>.shareWhileObserved(coroutineScope: CoroutineScope) = shareIn(
    scope = coroutineScope,
    started = SharingStarted.WhileSubscribed(),
    replay = 1
)