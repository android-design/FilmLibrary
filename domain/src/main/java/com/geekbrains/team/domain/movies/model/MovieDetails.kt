package com.geekbrains.team.domain.movies.model

import java.util.*

data class MovieDetails(
    val credits: Credits,
    val title: String,
    val originalTitle: String,
    val genres: String,
    val releaseDate: String,
    val productionCountries: String,
    val runTime: Int,
    val voteAverage: Double,
    val director: String,
    val writers: String,
    val producer: String
)
