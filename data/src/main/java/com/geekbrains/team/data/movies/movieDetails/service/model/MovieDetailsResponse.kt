package com.geekbrains.team.data.movies.movieDetails.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.domain.movies.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: String,
    val runtime: String,
    val status: String,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    data class ProductionCompany(
        val name: String,
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String?,
        @SerializedName("origin_country")
        val originCountry: String
    )

    data class ProductionCountry(
        val name: String,
        @SerializedName("iso_3166_1")
        val iso: String
    )

    data class SpokenLanguage(
        val name: String,
        @SerializedName("iso_639_1")
        val iso: String
    )
}

fun MovieDetailsResponse.toListOfGenreIds(): List<Int> = genres.map { _ ->  id}

fun MovieDetailsResponse.toMovie() = Movie(
    id = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    popularity = this.popularity,
    voteCount = this.voteCount,
    video = this.video,
    posterPath = this.posterPath,
    adult = this.adult,
    backdropPath = this.backdropPath,
    originalLanguage = this.originalLanguage,
    genreIds = this.toListOfGenreIds(),
    voteAverage = (this.voteAverage * 10).toInt(),
    overview = this.overview ?: Const.NO_OVERVIEW,
    releaseDate = this.releaseDate
)
/*
    val id: Int,
    val title: String,
    val originalTitle: String,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val posterPath: String?,
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val voteAverage: Int,
    val overview: String,
    val releaseDate: String
 */