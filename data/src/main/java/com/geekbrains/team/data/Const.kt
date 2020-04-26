package com.geekbrains.team.data

import android.annotation.SuppressLint
import java.util.*

object Const {
    @SuppressLint("ConstantLocale")
    val LANGUAGE = Locale.getDefault().toLanguageTag()
    const val BASE_URL = "https://api.themoviedb.org/"

    const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/w185/"
    const val POSTER_AND_BACKDROP_PREFIX = "https://image.tmdb.org/t/p/w500/"
    const val DATE_FORMAT = "yyyy-MM-dd"
}