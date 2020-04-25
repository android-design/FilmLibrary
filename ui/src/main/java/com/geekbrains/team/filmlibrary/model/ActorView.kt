package com.geekbrains.team.filmlibrary.model

import com.geekbrains.team.domain.movies.model.Credits
import com.geekbrains.team.domain.movies.model.Movie

data class ActorView(
    val id: Int,
    val name: String,
    val character: String,
    val posterPath: String
)

fun Credits.CastPerson.toActorView(): ActorView = ActorView(
    id = this.id,
    name = this.name,
    character = this.character,
    posterPath = this.profilePath ?: ""
)

fun Credits.CrewPerson.toActorView(): ActorView = ActorView(
    id = this.id,
    name = this.name,
    character = this.job,
    posterPath = this.profilePath ?: ""
)

fun Movie.Actor.toActorView(): ActorView = ActorView(
    id = this.id,
    name = this.name,
    character = this.character,
    posterPath = this.posterPath ?: ""
)

fun Movie.Member.toActorView(): ActorView = ActorView (
    id = this.id,
    name = this.name,
    character = this.job,
    posterPath = this.posterPath ?: ""
)