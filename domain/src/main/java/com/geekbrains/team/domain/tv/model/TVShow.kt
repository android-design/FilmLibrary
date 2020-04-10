package com.geekbrains.team.domain.tv.model

import com.geekbrains.team.domain.base.model.MovieAndTVShow

data class TVShow(
    val backdropPath: String,
    val createdBy: List<CreatedBy>? = null,
    val episodeRunTime: List<Int>? = null,
    val firstAirDate: String,
    val genres: List<Genre>? = null,
    val homepage: String = "",
    val id: Int,
    val trailer: String = "",
    val inProduction: Boolean? = null,
    val languages: List<String>? = null,
    val lastAirDate: String? = null,
    val lastEpisodeToAir: LastEpisodeToAir? = null,
    val name: String,
    val networks: List<Network>? = null,
    val nextEpisodeToAir: Any? = null,
    val numberOfEpisodes: Int? = null,
    val numberOfSeasons: Int? = null,
    val originCountry: List<String>? = null,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>? = null,
    val seasons: List<Season>? = null,
    val status: String? = null,
    val type: String? = null,
    val voteAverage: Double,
    val voteCount: Int,
    // TODO Add class for actors.
    val cast: List<String>? = null, // Актеры
    val images: List<String>? = null
) : MovieAndTVShow() {
    data class CreatedBy(
        val creditId: String,
        val gender: Int,
        val id: Int,
        val name: String,
        val profilePath: String
    )

    data class Genre(
        val id: Int,
        val name: String
    )

    data class LastEpisodeToAir(
        val airDate: String,
        val episodeNumber: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val productionCode: String,
        val seasonNumber: Int,
        val showId: Int,
        val stillPath: String,
        val voteAverage: Double,
        val voteCount: Int
    )

    data class Network(
        val id: Int,
        val logoPath: String,
        val name: String,
        val originCountry: String
    )

    data class ProductionCompany(
        val id: Int,
        val logoPath: String?,
        val name: String,
        val originCountry: String
    )

    data class Season(
        val airDate: String,
        val episodeCount: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val posterPath: String,
        val seasonNumber: Int
    )
}