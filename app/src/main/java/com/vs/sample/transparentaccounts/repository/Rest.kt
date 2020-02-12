package com.vs.sample.transparentaccounts.repository

import com.google.gson.GsonBuilder
import com.vs.sample.transparentaccounts.BuildConfig
import com.vs.sample.transparentaccounts.utils.Consts
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Rest {

    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val apiKeyInterceptor = Interceptor { chain ->
        val header = chain.request().headers.newBuilder().add("web-api-key", BuildConfig.TransparentApiKey).build()
        val request = chain.request()
            .newBuilder()
            .headers(header)
            .build()
        chain.proceed(request)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(Consts.URL_MOCK_2)
        .client(OkHttpClient.Builder().addInterceptor(apiKeyInterceptor).addInterceptor(interceptor).build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat(Consts.FORMAT_DATE_TIME_REST).create()))
        .build()

    private val service = retrofit.create(TAService::class.java)

    companion object {

        @Volatile private var instance: Rest? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Rest().also { instance = it }
            }
    }

    suspend fun accounts(page : Int? = null, size : Int? = null) = service.getAccounts(page, size)

    suspend fun transactionsOfAccount(id: String, page: Int? = null, size: Int? = null, order: String? = null, dateFrom: String? = null, dateTo: String? = null)
            = service.getTransactions(id, page, size, order, dateFrom, dateTo)

}