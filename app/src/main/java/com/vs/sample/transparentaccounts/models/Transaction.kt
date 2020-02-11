package com.vs.sample.transparentaccounts.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

data class Transaction(val type: String, val amount: Amount, val dueDate: Date, val sender: Sender, val receiver: Receiver): Serializable {
    val processingDate: Date? = null
    val typeDescription: String? = null
}

data class Amount(@SerializedName("value") val amount: BigDecimal, val precision: Int, val currency: String)
data class Receiver(val accountNumber: String, val bankCode: String, val iban: String)
data class Sender(val accountNumber: String, val bankCode: String, val iban: String) {
    val specificSymbol: String? = null
    val specificSymbolParty: String? = null
    val variableSymbol: String? = null
    val constantSymbol: String? = null
    val name: String? = null
    val description: String? = null
}

/*{
    "amount": {
        "value": -1.74,
        "precision": 0,
        "currency": "CZK"
    },
    "type": "40900",
    "dueDate": "2016-08-31T00:00:00",
    "processingDate": "2016-08-31T00:00:00",
    "sender": {
        "accountNumber": "000000-0000000000",
        "bankCode": "0800",
        "iban": "CZ13 0800 0000 0029 0647 8309",
        "specificSymbol": "0000000000",
        "specificSymbolParty": "0000000000",
        "variableSymbol": "0000000000",
        "constantSymbol": "0000",
        "name": "xxxxxxxxxxxx9126",
        "description": "KORBEL ŠTĚPÁN"
    },
    "receiver": {
        "accountNumber": "000000-2906478309",
        "bankCode": "0800",
        "iban": "CZ13 0800 0000 0029 0647 8309"
    },
    "typeDescription": "Poplatky"
}*/