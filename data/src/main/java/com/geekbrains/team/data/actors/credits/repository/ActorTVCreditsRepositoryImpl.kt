package com.geekbrains.team.data.actors.credits.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.actors.credits.service.ActorTVCreditsApi
import com.geekbrains.team.data.actors.credits.service.model.toActorCreditsInfo
import com.geekbrains.team.domain.actors.credits.repository.ActorTVCreditsRepository
import com.geekbrains.team.domain.actors.model.ActorCreditsInfo
import io.reactivex.Single
import javax.inject.Inject

class ActorTVCreditsRepositoryImpl @Inject constructor(
    private val api: ActorTVCreditsApi
): ActorTVCreditsRepository {
    override fun fetch(id: Int): Single<ActorCreditsInfo> {
        return api.getActorTVCredits(id = id, apiKey = BuildConfig.API_KEY, language = Const.LANGUAGE)
            .map { response -> response.toActorCreditsInfo() }
    }
}