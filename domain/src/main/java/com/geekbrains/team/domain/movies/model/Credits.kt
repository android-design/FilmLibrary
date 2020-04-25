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

fun Credits.CastPerson.toMovieActor(): Movie.Actor = Movie.Actor(
    id = this.id, name = this.name, character = this.character, posterPath = this.profilePath)

fun Credits.CrewPerson.toMovieMember(): Movie.Member = Movie.Member(
    id = this.id, name = this.name, job = this.job, posterPath = this.profilePath ?: ""
)