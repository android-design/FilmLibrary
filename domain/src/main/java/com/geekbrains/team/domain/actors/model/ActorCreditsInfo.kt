package com.geekbrains.team.domain.actors.model

data class ActorCreditsInfo(
    val id: Int,
    val cast: List<MovieInfo>,
    val crew: List<JobInfo>
) {
    data class MovieInfo(
        val id: Int,
        val character: String,
        val backdropPath: String?,
        val posterPath: String
    )

    data class JobInfo(
        val id: Int,
        val department: String,
        val job: String,
        val backdropPath: String?,
        val posterPath: String
    )
}