package com.geekbrains.team.data.movies.nowPlayingMovies.service.model
import com.google.gson.annotations.SerializedName


data class ResponseNowPlayingMovies(
    @SerializedName("results")
    val results: List<NowPlayingMovies>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class NowPlayingMovies(
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
        val backdropPath: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("title")
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Int,
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