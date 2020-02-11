package com.vs.sample.transparentaccounts.utils

import java.util.concurrent.ThreadLocalRandom

fun randomString(): String {
    return ThreadLocalRandom.current().nextLong().toString()
}