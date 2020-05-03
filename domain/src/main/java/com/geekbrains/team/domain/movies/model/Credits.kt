package com.geekbrains.team.domain.movies.model

data class Credits(
    val cast: List<CastPerson>?,
    val crew: List<CrewPerson>?
) {
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

    data class CrewPerson(

        val creditId: String,
        val department: String,
        val gender: Int?,
        val id: Int,
        val job: String,
        val name: String,
        val profilePath: String?
    )
}

fun Credits.CastPerson.toMovieActor(): Movie.Actor =
    Movie.Actor(
        id = id, name = name, character = character, posterPath = profilePath
    )

fun Credits.CrewPerson.toMovieMember(): Movie.Member =
    Movie.Member(
        id = id, name = name, job = job, posterPath = profilePath
    )