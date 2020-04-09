package com.geekbrains.team.domain.movies.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: Boolean? = null,
    val budget: Int? = 0,
    val genres: List<Genre>? = null,
    val id: Int,
    val images: List<String>? = null,
    val trailer: String = "",
    val homepage: String? = null,
    val imdbId: String? = "",
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>? = null,
    val productionCountries: List<ProductionCountry>? = null,
    val releaseDate: String,
    val revenue: Int = 0,
    val runtime: Int = 0,
    // TODO Add class for actors.
    val cast: List<String>? = null, // Актеры
    // TODO Add class for crew
    val crew: List<String>? = null, // Команда
    val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    data class ProductionCompany(
        val id: Int,
        val logoPath: Any?,
        val name: String,
        val originCountry: String
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String
    )

    data class SpokenLanguage(
        val iso6391: String,
        val name: String
    )
}