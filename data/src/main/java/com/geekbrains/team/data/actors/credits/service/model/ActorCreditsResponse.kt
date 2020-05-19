package com.geekbrains.team.data.actors.credits.service.model

import com.geekbrains.team.domain.actors.model.ActorCreditsInfo
import com.google.gson.annotations.SerializedName

data class ActorCreditsResponse(
    val id: Int,
    val cast: List<MovieInfo>,
    val crew: List<JobInfo>
) {
    data class MovieInfo(
        val id: Int,
        val character: String,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("poster_path")
        val posterPath: String

    )

    data class JobInfo(
        val id: Int,
        val department: String,
        val job: String,
        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("poster_path")
        val posterPath: String
    )
}

fun ActorCreditsResponse.toActorCreditsInfo() = ActorCreditsInfo(
    id = this.id,
    cast = this.cast.map{ ActorCreditsInfo.MovieInfo(
        id = it.id,
        character = it.character,
        backdropPath = it.backdropPath,
        posterPath = it.posterPath
    ) },
    crew = this.crew.map { ActorCreditsInfo.JobInfo (
        id = it.id,
        department = it.department,
        job = it.job,
        backdropPath = it.backdropPath,
        posterPath = it.posterPath
    ) }
)