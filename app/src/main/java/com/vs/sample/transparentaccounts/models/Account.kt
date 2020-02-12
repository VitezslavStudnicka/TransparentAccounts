package com.vs.sample.transparentaccounts.models

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

data class Account(val accountNumber: String,
                   val bankCode: String?,
                   val transparencyFrom: Date?,
                   val transparencyTo: Date?,
                   val publicationTo: Date?,
                   val actualizationDate: Date?,
                   val balance: BigDecimal?,
                   val currency: String?,
                   val name: String?,
                   val note: String?,
                   val description: String?,
                   val iban: String?
//                   val statements: List<String>?
                   ): Serializable

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

/*
{
    "pageNumber": 1,
    "pageSize": 1,
    "pageCount": 908,
    "nextPage": 2,
    "recordCount": 908,
    "accounts": [
    {
        "accountNumber": "000000-4384384389",
        "bankCode": "0800",
        "transparencyFrom": "2015-08-26T00:00:00",
        "transparencyTo": "3000-01-01T00:00:00",
        "publicationTo": "3000-01-01T00:00:00",
        "actualizationDate": "2020-02-12T21:00:14",
        "balance": 2486.83,
        "currency": "CZK",
        "name": " Římskokatolická farnost Manětín",
        "iban": "CZ93 0800 0000 0043 8438 4389"
    }
    ]
}*/
