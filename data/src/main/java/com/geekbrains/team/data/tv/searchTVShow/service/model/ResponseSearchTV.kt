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
            id = it.id,
            originalName = it.originalName,
            name = it.name,
            popularity = it.popularity,
            overview = it.overview,
            originCountry = it.originCountry,
            voteCount = it.voteCount,
            backdropPath = it.backdropPath?:"",
            originalLanguage = it.originalLanguage,
            voteAverage = it.voteAverage * 10,
            posterPath = it.posterPath?:"",
            firstAirDate = it.firstAirDate
        )
    }