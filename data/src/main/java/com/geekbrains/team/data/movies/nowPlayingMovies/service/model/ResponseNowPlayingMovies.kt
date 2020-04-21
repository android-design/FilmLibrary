package com.geekbrains.team.data.movies.nowPlayingMovies.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.data.parseToDate
import com.geekbrains.team.domain.movies.model.Movie
import com.google.gson.annotations.SerializedName


data class ResponseNowPlayingMovies(
    @SerializedName("results")
    val results: List<NowPlayingMovieDataModel>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class NowPlayingMovieDataModel(
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("poster_path")
        val posterPath: String?,
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

    data class Dates(
        @SerializedName("maximum")
        val maximum: String,
        @SerializedName("minimum")
        val minimum: String
    )
}

fun ResponseNowPlayingMovies.toMovie(): List<Movie> =
    results.map { movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            originalTitle = movie.originalTitle,
            popularity = (movie.popularity).toInt(),
            voteCount = movie.voteCount,
            video = movie.video,
            genreIds = movie.genreIds,
            posterPath = movie.posterPath?.let { Const.IMAGE_PREFIX + it } ?: "",
            adult = movie.adult,
            backdropPath = movie.backdropPath?.let { Const.POSTER_AND_BACKDROP_PREFIX + it } ?: "",
            originalLanguage = movie.originalLanguage,
            voteAverage = (movie.voteAverage * 10).toInt(),
            overview = movie.overview,
            releaseDate = movie.releaseDate.parseToDate()
        )
    }