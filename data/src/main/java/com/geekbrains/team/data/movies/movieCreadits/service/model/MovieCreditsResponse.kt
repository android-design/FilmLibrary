package com.geekbrains.team.data.movies.movieCreadits.service.model

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

fun