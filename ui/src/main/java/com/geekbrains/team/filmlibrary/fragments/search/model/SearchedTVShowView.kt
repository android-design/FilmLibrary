package com.geekbrains.team.filmlibrary.fragments.search.model

import com.geekbrains.team.domain.tv.model.TVShow

data class SearchedTVShowView(
    val id: Int,
    val originalName: String,
    val name: String,
    val popularity: String,
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

fun TVShow.toSearchedTVShowView() =
    SearchedTVShowView(
        id = id,
        originalName = originalName,
        name = name,
        popularity = popularity.toString(),
        genreIds = genreIds,
        overview = overview,
        originCountry = originCountry,
        voteCount = voteCount.toString(),
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage.toString(),
        posterPath = posterPath,
        firstAirDate = firstAirDate
    )