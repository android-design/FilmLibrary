package com.geekbrains.team.domain.tv.model

data class TVShow(
    val id: Int,
    val originalName: String,
    val name: String,
    val popularity: Double,
    val genreIds: List<Int>,
    val overview: String,
    val originCountry: List<String>,
    val voteCount: Int,
    val backdropPath: String,
    val originalLanguage: String,
    val voteAverage: Int,
    val posterPath: String,
    val firstAirDate: String
)