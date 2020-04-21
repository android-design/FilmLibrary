package com.geekbrains.team.filmlibrary

import java.text.SimpleDateFormat
import java.util.*

fun Date.parseToShortFormat(): String {
    val outputDateFormat = SimpleDateFormat("d MMM", Locale.getDefault())

    return outputDateFormat.format(this)
}

fun Date.parseToYear(): String {
    val outputDateFormat = SimpleDateFormat("yyyy", Locale.getDefault())

    return outputDateFormat.format(this)
}

fun Date.parseToNormalFormat(): String {
    val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return outputDateFormat.format(this)
}
