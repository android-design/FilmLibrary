package com.geekbrains.team.data.actors.details.service.model

import com.geekbrains.team.domain.actors.model.ActorInformation
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
    val placeOfBirthday: String?,

    @SerializedName("profile_path")
    val profilePath: String?,

    val homepage: String,

    @SerializedName("imdb_id")
    val imbdId: String?
)

fun ActorDetailsResponse.toActorInformation() =
    ActorInformation(
        id = this.id,
        name = this.name,
        birthday = this.birthday,
        deathDay = this.deathday,
        alsoKnownAs = this.alsoKnownAs,
        gender = this.gender,
        biography = this.biography,
        popularity = this.popularity,
        placeOfBirth = this.placeOfBirthday,
        profilePath = this.profilePath,
        homepage = this.homepage,
        imdbId = this.imbdId
    )