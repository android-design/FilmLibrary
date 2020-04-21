package com.geekbrains.team.data.tv.topRatedTV.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.data.getYear
import com.geekbrains.team.domain.tv.model.TVShow
import com.google.gson.annotations.SerializedName


data class ResponseTopRatedTV(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<TopRatedTV>
) {
    data class TopRatedTV(
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
        val firstAirDate: String?,
        @SerializedName("backdrop_path")
        val backdropPath: Any?,
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

fun ResponseTopRatedTV.toTVShow(): MutableList<TVShow> =
    results.map { tvShow ->
        TVShow(
            id = tvShow.id,
            originalName = tvShow.originalName,
            name = tvShow.name,
            popularity = (tvShow.popularity).toInt(),
            overview = tvShow.overview,
            originCountry = tvShow.originCountry,
            voteCount = tvShow.voteCount,
            backdropPath = tvShow.backdropPath?.let { Const.IMAGE_PREFIX + it } ?: "",
            originalLanguage = tvShow.originalLanguage,
            voteAverage = (tvShow.voteAverage * 10).toInt(),
            posterPath = tvShow.posterPath?.let { Const.POSTER_AND_BACKDROP_PREFIX + it } ?: "",
            firstAirDate = tvShow.firstAirDate?.getYear() ?: ""
        )
    }.toMutableList()