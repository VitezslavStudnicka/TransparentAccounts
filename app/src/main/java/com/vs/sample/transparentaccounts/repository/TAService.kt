package com.vs.sample.transparentaccounts.repository

import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.utils.Consts
import retrofit2.http.GET
import retrofit2.http.Query

interface TAService {

    @GET(Consts.ENDPOINT_ACCOUNTS)
    suspend fun getAccounts(@Query("page") page: Int?, @Query("size") size: Int?): List<Account>

}