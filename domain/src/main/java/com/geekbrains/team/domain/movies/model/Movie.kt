package com.geekbrains.team.domain.movies.model

import com.geekbrains.team.domain.base.model.Genre
import com.geekbrains.team.domain.base.model.Images
import com.geekbrains.team.domain.base.model.MovieAndTVShow
import com.geekbrains.team.domain.base.model.Video

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: Boolean? = null,
    val budget: Int? = 0,
    val genreIds: List<Int>? = null,
    val genres: MutableList<String> = ArrayList(),
    val id: Int,
    var videosNew: List<Video>? = null,
    var imagesNew: Images? = null,
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
) : MovieAndTVShow() {

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

fun fillMoviesGenres(
    moviesGenres: List<Genre>,
    movies: List<Movie>
): List<MovieAndTVShow> =
    movies.apply {
        val genresMoviesMap = moviesGenres.map { it.id to it.name }.toMap()

        map { movie ->
            movie.genreIds?.map {
                val result = genresMoviesMap[it]
                result?.let { movie.genres.add(result) }
            }
        }
    }