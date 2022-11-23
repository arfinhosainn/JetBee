package com.example.jetbee.util


sealed class Resource<T>(val data: T? = null, val message: String? = null, val result : Boolean? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, result: Boolean? =null) : Resource<T>(data, message, result)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
