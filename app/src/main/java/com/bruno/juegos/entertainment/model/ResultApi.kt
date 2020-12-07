package com.bruno.juegos.entertainment.model

sealed class ResultApi<out T> {

 data class Success<out T>(val data: T) : ResultApi<T>()
    data class Error(val exception: Exception) : ResultApi<Nothing>()

}
