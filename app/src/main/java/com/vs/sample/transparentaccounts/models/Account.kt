package com.vs.sample.transparentaccounts.models

import java.math.BigDecimal
import java.util.*

data class Account(val accountNumber: String) {
    val bankCode: String? = null
    val transparencyFrom: Date? = null
    val transparencyTo: Date? = null
    val publicationTo: Date? = null
    val actualizationDate: Date? = null
    val balance: BigDecimal? = null
    val currency: String? = null
    val name: String? = null
    val note: String? = null
    val description: String? = null
    val iban: String? = null
    val statements: List<String>? = null
}

/*
{
    "pageNumber": 0,
    "pageCount": 2,
    "pageSize": 1,
    "recordCount": 1,
    "nextPage": 1,
    "accounts": [
    {
        "accountNumber": "000000-2906478309",
        "bankCode": "0800",
        "transparencyFrom": "2016-08-18T00:00:00",
        "transparencyTo": "3000-01-01T00:00:00",
        "publicationTo": "3000-01-01T00:00:00",
        "actualizationDate": "2016-09-07T10:00:06",
        "balance": 1063961.87,
        "currency": "CZK",
        "name": "SVAZEK OBCÍ - REGION DOLNÍ BEROUNKA",
        "description": "sbírka pro Nepál",
        "note": "Příjmový účet",
        "iban": "CZ13 0800 0000 0029 0647 8309",
        "statements": [
        "RR_SK.COM..xml",
        "prevedeni-it-majetku-do-uzivani.pdf"
        ]
    }
    ]
}*/