package com.geekbrains.team.domain.movies.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val posterPath: String?,
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val voteAverage: Int,
    val overview: String,
    val releaseDate: String
)