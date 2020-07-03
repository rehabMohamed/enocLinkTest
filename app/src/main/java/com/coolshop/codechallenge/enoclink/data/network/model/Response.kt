package com.coolshop.codechallenge.enoclink.data.network.model

/**
 * General response wrapper to handle api success and failure
 */
sealed class Response<out T : Any> {

    class Success<out T : Any>(val data: T?) : Response<T>()

    class Error(val throwable: Throwable) : Response<Nothing>()
}