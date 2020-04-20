package com.geekbrains.team.data.common.genre.model

import com.geekbrains.team.domain.base.model.Genre
import com.google.gson.annotations.SerializedName


data class ResponseGenres(
    @SerializedName("genres")
    val genres: List<GenreDataModel>
) {
    data class GenreDataModel(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

fun ResponseGenres.toGenres(): List<Genre> =
    genres.map { Genre(it.id, it.name) }