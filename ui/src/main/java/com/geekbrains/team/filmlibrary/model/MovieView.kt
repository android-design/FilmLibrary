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
    val genres: String, // Жанры (строки)
    val backdropPath: String, // Картинка из фильма
    val productionCountries: String, // Страны производства (строка)
    val runtime: String, // Продолжительность (строка)
    val overview: String // Описание (строка)
)

fun Movie.toMovieView() = MovieView(
        id = id,
        title = title,
        originalTitle = originalTitle,
        popularity = popularity.toString(),
        releaseDate = releaseDate,
        images = images ?: listOf("https://image.tmdb.org/t/p/w500/3lu6iHT189M6SL8q9OSmISYDoop.jpg",
            "https://image.tmdb.org/t/p/w500/4VGR3bzjfVQ0skc8T1O92ieyKLa.jpg",
            "https://image.tmdb.org/t/p/w500/oZibj2AItah70g4CzFgOw3jiFln.jpg",
            "https://image.tmdb.org/t/p/w500/elvVHhtKYFLoGGhfyKhhA0wQ4kc.jpg",
            "https://image.tmdb.org/t/p/w500/axqGyWPzkN8WNdl6wGwOd3EdRKE.jpg"),
        trailer = trailer,
        posterPath = "https://image.tmdb.org/t/p/w185/$posterPath",
        genres = genres.toString(),
        backdropPath = "https://image.tmdb.org/t/p/w185/$backdropPath",
        productionCountries = productionCountries.toString(),
        runtime = runtime.toString(),
        overview = overview
    )