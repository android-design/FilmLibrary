package com.geekbrains.team.data.tv.similar.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.domain.tv.model.TVShow
import com.google.gson.annotations.SerializedName

data class SimilarTVShowsResponse (
    val page: Int,
    val results: List<SimilarTVShowResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
    ) {
        data class SimilarTVShowResponse(
            @SerializedName("popularity")
            val popularity: Double,
            @SerializedName("poster_path")
            val posterPath: String?,
            @SerializedName("id")
            val id: Int,
            @SerializedName("vote_count")
            val voteCount: Int,
            @SerializedName("backdrop_path")
            val backdropPath: String?,
            @SerializedName("original_language")
            val originalLanguage: String,
            @SerializedName("original_name")
            val originalName: String,
            @SerializedName("genre_ids")
            val genreIds: List<Int>,
            @SerializedName("name")
            val name: String,
            @SerializedName("vote_average")
            val voteAverage: Double,
            @SerializedName("overview")
            val overview: String,
            @SerializedName("first_air_date")
            val firstAirDate: String
        )
}

fun SimilarTVShowsResponse.toTVShowList(): List<TVShow> =
    results.map { response -> TVShow(
         backdropPath = response.backdropPath.let { Const.POSTER_AND_BACKDROP_PREFIX + it } ?: "",
         firstAirDate = response.firstAirDate,
         id = response.id,
         name = response.name,
         originalLanguage = response.originalLanguage,
         originalName = response.originalName,
         overview = response.overview,
         popularity = response.popularity,
         posterPath = response.posterPath?.let { Const.IMAGE_PREFIX + it } ?: "",
         voteAverage = response.voteAverage,
         voteCount = response.voteCount
    ) }