package com.vs.sample.transparentaccounts.repository.responses

import com.vs.sample.transparentaccounts.models.Account
import com.vs.sample.transparentaccounts.models.Transaction

class ResponseTransactions {
    val pageNumber: Int? = null
    val pageCount: Int? = null
    val pageSize: Int? = null
    val recordCount: Int? = null
    val nextPage: Int? = null
    val transactions: List<Transaction>? = null
}