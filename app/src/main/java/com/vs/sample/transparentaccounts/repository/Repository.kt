package com.vs.sample.transparentaccounts.repository

import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.models.Transaction

class Repository(private val rest: Rest) {

    suspend fun getAccounts(): List<Account>? = rest.accounts().accounts

    suspend fun getTransactions(id: String): List<Transaction>? = rest.transactionsOfAccount(id).transactions

    companion object {

        @Volatile private var instance: Repository? = null

        fun getInstance(rest: Rest) =
            instance ?: synchronized(this) {
                instance ?: Repository(rest).also { instance = it }
            }
    }
}