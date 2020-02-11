package com.vs.sample.transparentaccounts.utils

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun Date.format(pattern: String): String {
    return SimpleDateFormat(pattern).format(this)
}