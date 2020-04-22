package com.geekbrains.team.data

import com.geekbrains.team.data.Const.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun String.getYear(): String =
    if (this.length >= 4) {
        this.substring(0, 4)
    } else ""

fun String.parseToDate(): Date {
    val inputDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)

    inputDateFormat.parse(this)?.let {
        return it
    } ?: return Date(0)
}
