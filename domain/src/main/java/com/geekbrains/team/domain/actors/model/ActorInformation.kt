package com.geekbrains.team.domain.actors.model

data class ActorInformation(
    val id: Int,
    val name: String,
    val birthday: String?,
    val deathDay: String?,
    val gender: Int,
    val biography: String,
    val popularity: Double,
    var profilePath: String? = null,
    val alsoKnownAs: List<String>,
    val placeOfBirth: String?,
    val imdbId: String?,
    val homepage: String?,
    var movieCredits: ActorCreditsInfo? = null,
    var tvCredits: ActorCreditsInfo? = null
)
