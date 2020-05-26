package com.geekbrains.team.domain.actors.model

data class ActorCreditsInfo(
    val id: Int,
    val cast: List<MovieInfo>,
    val crew: List<JobInfo>
) {
    data class MovieInfo(
        val id: Int,
        val title: String,
        val character: String,
        val posterPath: String
    )

    data class JobInfo(
        val id: Int,
        val title: String,
        val job: String,
        val posterPath: String
    )
}