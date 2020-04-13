package com.geekbrains.team.filmlibrary.fragments.mainScreen.model

import com.geekbrains.team.domain.movies.model.Movie

data class UpcomingMovieView(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val popularity: String,
    val voteCount: String,
    val trailer: String,
    val posterPath: String,
    val backdropPath: String,
    val originalLanguage: String,
    val genres: String,
    val voteAverage: String,
    val overview: String,
    val releaseDate: String
)

fun Movie.toUpcomingMovieView(): UpcomingMovieView = UpcomingMovieView(
    id = id,
    title = title,
    originalTitle = originalTitle,
    popularity = popularity.toString(),
    voteCount = voteCount.toString(),
    trailer = trailer,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    genres = genres?.map { it.name }.toString(),
    voteAverage = voteAverage.toString(),
    overview = overview,
    releaseDate = releaseDate
)