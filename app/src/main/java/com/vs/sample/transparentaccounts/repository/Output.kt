package com.vs.sample.transparentaccounts.repository

import java.lang.Exception

sealed class Output<out T : Any>{
    data class Success<out T : Any>(val output : T) : Output<T>()
    data class NetworkError(val exception: Exception)  : Output<Nothing>()
    data class ServerError(val errorString: String): Output<Nothing>()
}
