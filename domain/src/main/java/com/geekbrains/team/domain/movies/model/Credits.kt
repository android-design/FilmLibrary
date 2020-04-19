package com.geekbrains.team.domain.movies.model

data class Credits(
    val cast: List<CastPerson>,
    val crew: List<CrewPerson>
)