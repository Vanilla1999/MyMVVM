package com.example.mymvvm.tools

sealed class ResponseShopItem<out T> {
    data class Success<out T>(val value: T) : ResponseShopItem<T>()
    data class Failure(
        val errorCode: Int?,
        val errorBody: String?,
    ) : ResponseShopItem<Nothing>()

    object Loading : ResponseShopItem<Nothing>()
    object Empty : ResponseShopItem<Nothing>()
}
sealed class ResponseError {
    data class Failure(
        val errorBody: String?,
    ) : ResponseError()
    data class FailureUnknown(
        val errorBody: String?,
    ) : ResponseError()
}