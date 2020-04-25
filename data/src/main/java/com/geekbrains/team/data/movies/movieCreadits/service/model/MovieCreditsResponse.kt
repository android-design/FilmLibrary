package com.geekbrains.team.data.movies.movieCreadits.service.model

import com.geekbrains.team.data.Const
import com.geekbrains.team.domain.movies.model.Credits
import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Member>
) {
    data class Cast(
        @SerializedName("cast_id")
        val castId: Int,
        val character: String,
        @SerializedName("credit_id")
        val creditId: String,
        val gender: Int?,
        val id: Int,
        val name: String,
        val order: Int,
        @SerializedName("profile_path")
        val profilePath: String?
    )

    data class Member(
        @SerializedName("credit_id")
        val creditId: String,
        val department: String,
        val gender: Int?,
        val id: Int,
        val job: String,
        val name: String,
        @SerializedName("profile_path")
        val profilePath: String?
    )
}

fun MovieCreditsResponse.Cast.toCastPerson(): Credits.CastPerson = Credits.CastPerson(
    castId = this.castId,
    character = this.character,
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    name = this.name,
    order = this.order,
    profilePath = this.profilePath?.let { Const.IMAGE_PREFIX + it } ?: ""
)

fun MovieCreditsResponse.Member.toCrewPerson(): Credits.CrewPerson = Credits.CrewPerson(
    creditId = this.creditId,
    department = this.department,
    gender = this.gender,
    id = this.id,
    job = this.job,
    name = this.name,
    profilePath = this.profilePath.let { Const.IMAGE_PREFIX + it }
)

fun MovieCreditsResponse.toCredits(): Credits = Credits(
    cast = cast.map { it.toCastPerson() },
    crew = crew.map { it.toCrewPerson() }
)