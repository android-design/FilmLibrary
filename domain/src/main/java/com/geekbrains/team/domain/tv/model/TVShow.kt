package com.geekbrains.team.domain.tv.model

data class TVShow(
    val backdropPath: String,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val trailer: String,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val nextEpisodeToAir: Any?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val seasons: List<Season>,
    val status: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int,
    // TODO Add class for actors.
    val cast: List<String>, // Актеры
    val images: List<String>
) {
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