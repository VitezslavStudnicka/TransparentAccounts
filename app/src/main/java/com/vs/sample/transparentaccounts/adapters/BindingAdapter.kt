package com.vs.sample.transparentaccounts.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.vs.sample.transparentaccounts.utils.Consts
import com.vs.sample.transparentaccounts.utils.format
import java.util.*

@BindingAdapter("dateToText")
fun bindDateText(view: TextView, date: Date?) {
    view.text = date?.format(Consts.FORMAT_DATE_TIME_USER) ?: "-"
}