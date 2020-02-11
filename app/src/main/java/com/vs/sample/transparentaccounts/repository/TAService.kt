package com.vs.sample.transparentaccounts.repository

import com.vs.sample.transparentaccounts.repository.responses.ResponseAccounts
import com.vs.sample.transparentaccounts.repository.responses.ResponseTransactions
import com.vs.sample.transparentaccounts.utils.Consts
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TAService {

    @GET(Consts.ENDPOINT_ACCOUNTS)
    suspend fun getAccounts(@Query("page") page: Int?, @Query("size") size: Int?): ResponseAccounts

    @GET(Consts.ENDPOINT_TRANSACTIONS)
    suspend fun getTransactions(@Path("id") id: String, @Query("page") page: Int? = null, @Query("size") size: Int? = null, @Query("order") order: String? = null,
                                @Query("dateFrom") dateFrom: String? = null, @Query("dateTo") dateTo: String? = null): ResponseTransactions

}