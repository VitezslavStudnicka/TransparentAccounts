package com.vs.sample.transparentaccounts.repository

import android.util.Log
import com.google.gson.Gson
import com.vs.sample.transparentaccounts.repository.responses.ErrorResponse
import retrofit2.Response
import java.io.IOException


// TODO Proper error handling
open class SafeRepository {
    private val tag = SafeRepository::class.simpleName

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): T? {
        val result = apiOutput(call)
        var output: T? = null
        when (result) {
            is Output.Success -> output = result.output
            is Output.NetworkError -> Log.e(tag, "${result.exception}")
            is Output.ServerError -> {
                try {
                    val errorResponse = Gson().fromJson(result.errorString, ErrorResponse::class.java)
                    errorResponse?.let {
                        Log.e(tag, it.toString())
                    }
                } catch (e: Exception) {
                    Log.e(tag, "Parsing error with ${result.errorString}")
                }
            }
        }
        return output
    }

    private suspend fun <T : Any> apiOutput(call: suspend () -> Response<T>): Output<T> {
        try {
            val response = call.invoke()
            return if (response.isSuccessful)
                Output.Success(response.body()!!)
            else
                response.errorBody()?.let {
                    Output.ServerError(it.string())
                } ?: Output.NetworkError(IOException("Unknown exception"))
        } catch (e: Exception) {
            return Output.NetworkError(e)
        }
    }
}