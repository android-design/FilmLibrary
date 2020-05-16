package com.geekbrains.team.data

import android.util.Log
import com.geekbrains.team.data.Const.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun String?.getYear(): String =
    this?.let {
        if (this.length >= 4) {
            this.substring(0, 4)
        } else ""
    } ?: ""


fun String?.parseToDate(): Date {
    val inputDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
    this?.let { parsedDate ->
        try {
            inputDateFormat.parse(parsedDate)?.let {
                return it
            } ?: return Date(0)
        } catch (e: Exception) {
            Log.d("Error parse to date", "" + e.localizedMessage)
        }
    }

    return Date(0)
}
