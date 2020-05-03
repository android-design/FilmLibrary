package com.geekbrains.team.filmlibrary.model

import com.geekbrains.team.domain.movies.model.Movie

data class PersonView(
    val id: Int,
    val name: String,
    val character: String,
    val posterPath: String
)

fun Movie.Actor.toPersonView(): PersonView = PersonView(
    id = id,
    name = name,
    character = character,
    posterPath = posterPath ?: ""
)

fun Movie.Member.toPersonView(): PersonView = PersonView(
    id = id,
    name = name,
    character = job,
    posterPath = posterPath ?: ""
)