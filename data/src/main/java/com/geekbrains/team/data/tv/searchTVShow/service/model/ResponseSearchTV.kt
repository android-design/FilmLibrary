package com.geekbrains.team.data.tv.searchTVShow.service.model

import com.geekbrains.team.domain.tv.model.TVShow
import com.google.gson.annotations.SerializedName


data class ResponseSearchTVShow(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<SearchTVShow>
) {
    data class SearchTVShow(
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("name")
        val name: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("origin_country")
        val originCountry: List<String>,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String?
    )
}

fun ResponseSearchTVShow.toTVShow(): List<TVShow> =
    results.map {
        TVShow(
            it.id,
            it.originalName,
            it.name,
            it.popularity,
            it.genreIds,
            it.overview,
            it.originCountry,
            it.voteCount,
            it.backdropPath?:"",
            it.originalLanguage,
            (it.voteAverage * 10).toInt(),
            it.posterPath?:"",
            it.firstAirDate
        )
    }