package com.geekbrains.team.domain.actors.details.repository

import io.reactivex.Single

interface ActorMovieCreditsRepository {
    fun fetch(): Single<ActroCreditsInfo>
}