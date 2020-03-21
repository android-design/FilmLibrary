package com.geekbrains.team.domain.model

import java.util.*

data class Movie (
    val id: Int,
    val genres: List<String>,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: String,
    val realiseDate: Date,
    val trailerURL: String
)
