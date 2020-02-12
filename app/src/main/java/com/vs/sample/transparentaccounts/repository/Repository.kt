package com.vs.sample.transparentaccounts.repository

import java.util.*

class Repository private constructor(private val rest: Rest): SafeRepository() {

    suspend fun getAccounts(page: Int? = null, size: Int? = null, order: String? = null) = safeApiCall {rest.accounts(page, size)}

    suspend fun getTransactions(id: String, page: Int? = null, size: Int? = null, dateFrom: Date? = null, dateTo: Date? = null) = safeApiCall {rest.transactionsOfAccount(id, page, size, dateTo = dateTo)}

    companion object {

        @Volatile private var instance: Repository? = null

        fun getInstance(rest: Rest) =
            instance ?: synchronized(this) {
                instance ?: Repository(rest).also { instance = it }
            }
    }
}