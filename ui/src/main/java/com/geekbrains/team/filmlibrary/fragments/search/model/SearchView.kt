package com.geekbrains.team.filmlibrary.fragments.search.model

import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.tv.model.TVShow

sealed class SearchView

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
):SearchView()

data class SearchedMovieView(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val popularity: String,
    val voteCount: String,
    val posterPath: String,
    val backdropPath: String,
    val originalLanguage: String,
    val genres: String,
    val voteAverage: String,
    val overview: String,
    val releaseDate: String
):SearchView()

fun Movie.toSearchedMovieView(): SearchedMovieView =
    SearchedMovieView(
        id = id,
        title = title,
        originalTitle = originalTitle,
        popularity = popularity.toString(),
        voteCount = voteCount.toString(),
        posterPath = posterPath,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        genres = genres?.map { it.name }.toString(),
        voteAverage = voteAverage.toString(),
        overview = overview,
        releaseDate = releaseDate
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