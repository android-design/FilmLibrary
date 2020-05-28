package com.geekbrains.team.data.actors.credits.service.model

import com.geekbrains.team.data.Const
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
        val title: String,


        @SerializedName("poster_path")
        val posterPath: String?
    )

    data class JobInfo(
        val id: Int,
        val department: String,
        val job: String,
        val title: String,

        @SerializedName("poster_path")
        val posterPath: String?
    )
}

fun ActorCreditsResponse.toActorCreditsInfo() = ActorCreditsInfo(
    id = this.id,
    cast = this.cast.map{ ActorCreditsInfo.MovieInfo(
        id = it.id,
        title = it.title,
        character = it.character,
        posterPath =  Const.IMAGE_PREFIX + it.posterPath
    ) },
    crew = this.crew.map { ActorCreditsInfo.JobInfo (
        id = it.id,
        title = it.title,
        job = it.job,
        posterPath = Const.IMAGE_PREFIX + it.posterPath
    ) }
)