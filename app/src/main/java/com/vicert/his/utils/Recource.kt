package com.vicert.his.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null) : Resource<T>()
    data class Error (val msg: String?) : Resource<Nothing>()
}