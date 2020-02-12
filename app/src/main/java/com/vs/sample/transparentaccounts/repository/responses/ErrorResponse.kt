package com.vs.sample.transparentaccounts.repository.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(val error: ErrorItem?)

data class ErrorItem(val statusCode: Int?, val status: String?, val message: String?, val stackTrace: String?)

/*data class ErrorResponse(val status: String) {
    val errors: List<ErrorItem>? = null
    @SerializedName("cz-transactionId")
    val transactionId: String? = null
}

class ErrorItem {
    val error: String? = null
    val scope: String? = null
    val parameters: Any? = null
}*/
