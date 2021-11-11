package com.app.blingy.reauty.core.util


/**
 * to future prevent from exhausting the when block
 * @see [https://medium.com/androiddevelopers/sealed-with-a-class-a906f28ab7b5](Sealed with a class)
 */
val <T> T.exhaustive: T
    get() = this

/**
 *
 */
sealed class ResultOf<T>(
    open val data: T? = null,
    open val throwable: Throwable? = null
) {
    data class Success<T>(override val data: T) : ResultOf<T>(data)
    data class Failure<T>(override val throwable: Throwable) : ResultOf<T>(throwable = throwable)
}