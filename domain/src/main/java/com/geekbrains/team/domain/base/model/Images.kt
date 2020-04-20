package com.geekbrains.team.domain.base.model

data class Images(
    val backdrops: List<Backdrop>,
    val posters: List<Poster>
) {
    data class Backdrop(val url: String)
    data class Poster(val url: String)
}