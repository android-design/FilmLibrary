package com.geekbrains.team.domain.actors.details.repository

import com.geekbrains.team.domain.actors.model.ActorInformation
import io.reactivex.Single

interface ActorDetailsRepository {
    fun fetch(id: Int): Single<ActorInformation>
}