package com.geekbrains.team.filmlibrary.model

import com.geekbrains.team.domain.movies.model.Movie

data class MovieView(
    val id: Int,
    val title: String, // Название (строка)
    val originalTitle: String, // Оригинальное название (строка)
    val popularity: String, // Рейтинг imdb (строка)
    val releaseDate: String, // Дата выхода (строка, для будущих)
    val images: List<String>, // Массив больших фото (строки, url)
    val trailer: String, // Ссылка на трейлер (строка, url)
    val posterPath: String, // Маленькое фото (постер, строка, url)
    val genres: List<String>, // Жанры (строки)
    val backdropPath: String, // Картинка из фильма
    val productionCountries: String, // Страны производства (строка)
    val runtime: String, // Продолжительность (строка)
    val overview: String // Описание (строка)
)

fun Movie.toMovieView() =
    MovieView(
        id = id,
        title = title,
        originalTitle = originalTitle,
        popularity = popularity.toString(),
        releaseDate = releaseDate,
        images = images ?: listOf(),
        trailer = trailer,
        posterPath = posterPath,
        genres = genres?.map { it.name }?: listOf(),
        backdropPath = backdropPath,
        productionCountries = productionCountries.toString(),
        runtime = runtime.toString(),
        overview = overview
    )