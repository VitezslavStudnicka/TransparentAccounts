package com.vs.sample.transparentaccounts.repository.responses

data class ErrorResponse(val error: ErrorItem?)

data class ErrorItem(val statusCode: Int?, val status: String?, val message: String?, val stackTrace: String?)