package com.app.blingy.reauty.core.util.extension

sealed class ResourcesState<T>( val data : T? = null,val message : String ? = null){
    class Success<T>(data: T) : ResourcesState<T>(data)
    class Error<T>(message: String, data: T? = null ) : ResourcesState<T>(data,message)
    class Loading<T>(data: T? = null) : ResourcesState<T>(data)
}
