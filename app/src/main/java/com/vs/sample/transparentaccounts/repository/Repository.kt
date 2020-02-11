package com.vs.sample.transparentaccounts.repository

import com.vs.sample.transparentaccounts.models.Account

class Repository(private val rest: Rest) {

    suspend fun getAccounts(): List<Account>? = rest.accounts().accounts

    companion object {

        @Volatile private var instance: Repository? = null

        fun getInstance(rest: Rest) =
            instance ?: synchronized(this) {
                instance ?: Repository(rest).also { instance = it }
            }
    }
}