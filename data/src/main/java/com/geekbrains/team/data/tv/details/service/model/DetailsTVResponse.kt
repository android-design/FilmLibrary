package com.geekbrains.team.data.tv.details.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.data.getYear
import com.geekbrains.team.domain.tv.model.TVShow
import com.google.gson.annotations.SerializedName

data class DetailsTVResponse(
    val id: Int,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("created_by")
    val creators: List<Creator>,

    @SerializedName("episode_run_time")
    val runTime: List<Int>,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    val genres: List<Genre>,
    val homepage: String,

    @SerializedName("in_production")
    val inProduction: Boolean,

    val languages: List<String>,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    val name: String,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<Company>,

    val seasons: List<Season>,
    val status: String,
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
) {
    data class Creator(
        val id: Int,
        val name: String,

        @SerializedName("credit_id")
        val creditId: String,

        val gender: Int,

        @SerializedName("profile_path")
        val profilePath: String
    )

    data class Genre(
        val id: Int,
        val name: String
    )

    data class Company(
        val id: Int,

        @SerializedName("logo_path")
        val path: String?,

        val name: String,
        @SerializedName("origin_country")

        val country: String
    )

    data class Season(
        val id: Int,

        @SerializedName("air_date")
        val airDate: String,

        @SerializedName("episode_count")
        val episodeCount: Int,

        val name: String,
        val overview: String,

        @SerializedName("poster_path")
        val poster: String,

        @SerializedName("season_number")
        val seasonNumber: Int
    )
}

fun DetailsTVResponse.toTVShow(): TVShow = TVShow(
    id = this.id,
    originalName = this.originalName,
    name = this.name,
    popularity = (this.popularity).toInt(),
    overview = this.overview,
    originCountry = this.originCountry,
    voteCount = this.voteCount,
    backdropPath = this.backdropPath?.let { Const.IMAGE_PREFIX + it } ?: "",
    originalLanguage = this.originalLanguage,
    voteAverage = (this.voteAverage * 10).toInt(),
    posterPath = this.posterPath?.let { Const.POSTER_AND_BACKDROP_PREFIX + it } ?: "",
    firstAirDate = this.firstAirDate.getYear(),
    numberOfSeasons = this.numberOfSeasons
)