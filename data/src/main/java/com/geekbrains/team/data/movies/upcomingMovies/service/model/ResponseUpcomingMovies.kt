package com.geekbrains.team.data.movies.upcomingMovies.service.model

import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import com.google.gson.annotations.SerializedName


data class ResponseUpcomingMovies(
    @SerializedName("results")
    val results: List<UpcomingMovie>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class UpcomingMovie(
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("title")
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String
    )
}

fun ResponseUpcomingMovies.toUpcomingMovie(): List<UpcomingMovie> {
    return results.map { movie ->
        UpcomingMovie(
            id = movie.id,
            title = movie.title,
            originalTitle = movie.originalTitle,
            popularity = movie.popularity,
            voteCount = movie.voteCount,
            video = movie.video,
            posterPath = movie.posterPath,
            adult = movie.adult,
            backdropPath = movie.backdropPath ?: "",
            originalLanguage = movie.originalLanguage,
            genreIds = movie.genreIds,
            voteAverage = (movie.voteAverage*10).toInt(),
            overview = movie.overview,
            releaseDate = movie.releaseDate
        )
    }
}