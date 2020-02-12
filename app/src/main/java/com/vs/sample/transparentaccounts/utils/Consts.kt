package com.vs.sample.transparentaccounts.utils

object Consts {
    const val URL = "https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v3/transparentAccounts/"
    const val URL_MOCK = "https://jsapi.apiary.io/apis/eahtransparentaccountsv3prod/http-transactions/"
    const val URL_MOCK_2 = "https://private-anon-158726cba1-eahtransparentaccountsv3prod.apiary-mock.com/webapi/api/v3/transparentAccounts/"

    const val FORMAT_DATE_TIME_REST = "yyyy-MM-dd'T'HH:mm:ss"
    const val FORMAT_DATE_TIME_USER = "HH:mm dd.MM.yyyy"

    const val ENDPOINT_ACCOUNTS = "."
    const val ENDPOINT_ACCOUNT_DETAIL = "{id}"
    const val ENDPOINT_TRANSACTIONS = "{id}/transactions/"

    const val DEFAULT_PAGE_SIZE = 25
    const val DEFAULT_PREFETCHSIZE_SIZE = 10
}