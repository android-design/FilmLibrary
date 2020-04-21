package com.geekbrains.team.domain.movies.model

data class CrewPerson(

    val creditId: String,
    val department: String,
    val gender: Int?,
    val id: Int,
    val job: String,
    val name: String,
    val profilePath: String?
)