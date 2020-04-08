package com.geekbrains.team.filmlibrary.model

import com.geekbrains.team.domain.tv.model.TVShow

data class TVShowView(
    val id: Int,
    val originalName: String,
    val name: String,
    val popularity: String,
    val trailer: String,
    val genreIds: List<Int>,
    val overview: String,
    val originCountry: List<String>,
    val voteCount: String,
    val backdropPath: String,
    val originalLanguage: String,
    val voteAverage: String,
    val posterPath: String,
    val firstAirDate: String
)

fun TVShow.toTVShowView() =
    TVShowView(
        id = id,
        originalName = originalName,
        name = name,
        popularity = popularity.toString(),
        trailer = trailer,
        genreIds = genreIds,
        overview = overview,
        originCountry = originCountry,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage.toString(),
        posterPath = posterPath,
        firstAirDate = firstAirDate,
        voteCount = voteCount.toString()
    )