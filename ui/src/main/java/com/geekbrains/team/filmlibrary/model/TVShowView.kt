package com.geekbrains.team.filmlibrary.model

import com.geekbrains.team.domain.tv.model.TVShow

data class TVShowView(
    val id: Int,
    val originalName: String, // Оригинальное название (строка)
    val name: String, // Название (строка)
    val numberOfSeasons: String, // Количество сезонов (строка)
    val popularity: String,  // Рейтинг imdb
    val lastAirDate: String, // Дата выхода (строка, для будущих)
    val images: List<String>, // Массив больших фото (строки, url)
    val trailer: String, // Ссылка на трейлер (строка, url)
    val posterPath: String, // Маленькое фото (постер, строка, url)
    val genres: String, // Жанры (строки)
    val firstAirDate: String, // Год выхода первого сезона (строка, для уже вышедших. можно объединить, по аналогии с фильмами, если там объединяем)
    val originCountry: String, // Страны производства (строка)
    val episodeRunTime: Int,
    val overview: String, // Описание
    val cast: List<String>, // Актёры (по идее массив моделей актёров)
    val productionCompanies: String // Площадка, на которой показывался (строка)
)

fun TVShow.toTVShowView() =
    TVShowView(
        id = id,
        originalName = originalName,
        name = name,
        numberOfSeasons = numberOfSeasons.toString(),
        popularity = popularity.toString(),
        trailer = trailer,
        images = images ?: listOf("https://image.tmdb.org/t/p/w500/3lu6iHT189M6SL8q9OSmISYDoop.jpg",
            "https://image.tmdb.org/t/p/w500/4VGR3bzjfVQ0skc8T1O92ieyKLa.jpg",
            "https://image.tmdb.org/t/p/w500/oZibj2AItah70g4CzFgOw3jiFln.jpg",
            "https://image.tmdb.org/t/p/w500/elvVHhtKYFLoGGhfyKhhA0wQ4kc.jpg",
            "https://image.tmdb.org/t/p/w500/axqGyWPzkN8WNdl6wGwOd3EdRKE.jpg"),
        firstAirDate = firstAirDate,
        episodeRunTime = episodeRunTime?.firstOrNull() ?: 0,
        lastAirDate = lastAirDate ?: "",
        genres = genres.joinToString(),
        overview = overview,
        originCountry = originCountry.toString(),
        posterPath = posterPath,
        cast = cast ?: listOf(),
        productionCompanies = productionCompanies?.map { it.name }.toString()
    )