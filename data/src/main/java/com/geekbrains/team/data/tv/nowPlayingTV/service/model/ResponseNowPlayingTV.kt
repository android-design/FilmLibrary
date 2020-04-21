package com.geekbrains.team.data.tv.nowPlayingTV.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.data.getYear
import com.geekbrains.team.domain.tv.model.TVShow
import com.google.gson.annotations.SerializedName


data class ResponseNowPlayingTV(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<NowPlayingTVDataModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class NowPlayingTVDataModel(
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: List<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun ResponseNowPlayingTV.toTVShow(): List<TVShow> =
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
            voteAverage = tvShow.voteAverage * 10,
            posterPath = tvShow.posterPath?.let { Const.IMAGE_PREFIX + it } ?: "",
            firstAirDate = tvShow.firstAirDate?.getYear() ?: ""
        )
    }
