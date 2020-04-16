package com.geekbrains.team.data

fun String.getYear(): String {
    if (this.length >= 4) {
        return this.substring(0, 4)
    }

    return ""
}