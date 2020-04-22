package com.geekbrains.team.data.movies.movieCreadits.service.model

import com.geekbrains.team.domain.movies.model.CastPerson
import com.geekbrains.team.domain.movies.model.Credits
import com.geekbrains.team.domain.movies.model.CrewPerson
import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    val id: Int,
    val casts: List<Cast>,
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

fun MovieCreditsResponse.Cast.toCastPerson(): CastPerson = CastPerson(
    castId = this.castId,
    character = this.character,
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    name = this.name,
    order = this.order,
    profilePath = this.profilePath
)

fun MovieCreditsResponse.Member.toCrewPerson(): CrewPerson = CrewPerson(
    creditId = this.creditId,
    department = this.department,
    gender = this.gender,
    id = this.id,
    job = this.job,
    name = this.name,
    profilePath = this.profilePath
)

fun MovieCreditsResponse.toCredits(): Credits = Credits(
    crew = this.crew.map { member -> member.toCrewPerson() },
    cast = this.casts.map { cast -> cast.toCastPerson() }
)