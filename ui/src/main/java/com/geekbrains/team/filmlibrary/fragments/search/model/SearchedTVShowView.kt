package com.geekbrains.team.filmlibrary.fragments.search.model

import com.geekbrains.team.domain.tv.model.TVShow

data class SearchedTVShowView(
    val id: Int,
    val originalName: String,
    val name: String,
    val popularity: String,
    val genres: String,
    val overview: String,
    val originCountry: String,
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
        genres = genres?.map { it.name }.toString(),
        overview = overview,
        originCountry = originCountry?.map { name }.toString(),
        voteCount = voteCount.toString(),
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage.toString(),
        posterPath = posterPath,
        firstAirDate = firstAirDate
    )