package com.geekbrains.team.data.movies.searchMovies.service.model

import com.geekbrains.team.data.Const.IMAGE_PREFIX
import com.geekbrains.team.data.Const.POSTER_AND_BACKDROP_PREFIX
import com.geekbrains.team.data.parseToDate
import com.geekbrains.team.domain.movies.model.Movie
import com.google.gson.annotations.SerializedName


data class ResponseSearchMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<SearchMovieResult>
) {
    data class SearchMovieResult(
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("id")
        val id: Int,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("title")
        val title: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String?
    )
}

fun ResponseSearchMovies.toSearchMovie(): List<Movie> =
    results.map { movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            originalTitle = movie.originalTitle,
            popularity = (movie.popularity).toInt(),
            voteCount = movie.voteCount,
            genreIds = movie.genreIds,
            video = movie.video,
            posterPath = movie.posterPath?.let { IMAGE_PREFIX + it } ?: "",
            adult = movie.adult,
            backdropPath = movie.backdropPath?.let { POSTER_AND_BACKDROP_PREFIX + it } ?: "",
            originalLanguage = movie.originalLanguage,
            voteAverage = (movie.voteAverage * 10).toInt(),
            overview = movie.overview,
            releaseDate = movie.releaseDate.parseToDate()
        )
    }