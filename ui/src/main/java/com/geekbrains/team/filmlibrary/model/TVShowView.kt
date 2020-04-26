package com.geekbrains.team.filmlibrary.model

import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.filmlibrary.Const

data class TVShowView(
    val id: Int,
    val originalName: String, // Оригинальное название (строка)
    val name: String, // Название (строка)
    val numberOfSeasons: String, // Количество сезонов (строка)
    val voteAverage: String,  // Рейтинг imdb
    val lastAirDate: String, // Дата выхода (строка, для будущих)
    val images: List<String>, // Массив больших фото (строки, url)
    val trailer: String, // Ссылка на трейлер (строка, url)
    val posterPath: String, // Маленькое фото (постер, строка, url)
    val backdropPath: String,
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
        backdropPath = backdropPath,
        name = name,
        numberOfSeasons = numberOfSeasons.toString(),
        voteAverage = voteAverage.toString(),
        trailer = trailer(),
        // TODO Fix this to feature.
        images = images ?: listOf(),
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

private fun TVShow.trailer(): String = videos?.firstOrNull {
    it.site.equals(Const.YOUTUBE, true) && it.type.equals(
        Const.TRAILER,
        true
    )
}?.let {
    Const.YOUTUBE_SITE + it.key
} ?: ""