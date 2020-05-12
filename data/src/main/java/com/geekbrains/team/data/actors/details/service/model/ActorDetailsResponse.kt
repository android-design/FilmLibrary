package com.geekbrains.team.data.actors.details.service.model

import com.google.gson.annotations.SerializedName


data class ActorDetailsResponse(
    val birthday: String?,
    val deathday: String?,
    val id: Int,
    val name: String,

    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>,
    val gender: Int,
    val biography: String,
    val popularity: Double,

    @SerializedName("place_of_birth")


)