package com.geekbrains.team.domain.movies.model

data class CastPerson(

    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int?,
    val id: Int,
    val name: String,
    val order: Int,
    val profilePath: String?
)