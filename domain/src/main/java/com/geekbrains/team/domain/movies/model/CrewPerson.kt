package com.geekbrains.team.domain.movies.model

data class CrewPerson(

    val creditId: String,
    val department: String,
    val gender: Int?,
    val id: Int,
    val job: String,
    val name: String,
    val profilePath: String?
) {
}

/*
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
 */