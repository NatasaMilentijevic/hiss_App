package utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error (val msg: String?) : Resource<Nothing>()
}