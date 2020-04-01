package com.geekbrains.team.data.movies.topRatedMovies.service

import com.geekbrains.team.domain.movies.topRatedMovies.model.TopRatedMovie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseTopRatedMovies(
    @Expose
    val results: List<TopRatedMovie>,

    @Expose
    val page: Int,

    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,

    @Expose
    @SerializedName("total_result")
    val totalResult: Int
) {
    data class TopRatedMovie(
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

fun ResponseTopRatedMovies.toTopRatedMovie(): List<TopRatedMovie> {
    return results.map { movie ->
        TopRatedMovie(
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