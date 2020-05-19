package com.geekbrains.team.data.actors.credits.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.actors.credits.service.ActorMovieCreditsApi
import com.geekbrains.team.data.actors.credits.service.model.toActorCreditsInfo
import com.geekbrains.team.domain.actors.details.repository.ActorMovieCreditsRepository
import com.geekbrains.team.domain.actors.model.ActorCreditsInfo
import io.reactivex.Single
import javax.inject.Inject

class ActorMovieCreditsRepositoryImpl @Inject constructor(
    private val api: ActorMovieCreditsApi
): ActorMovieCreditsRepository {
    override fun fetch(id: Int): Single<ActorCreditsInfo> {
        return api.getActorMovieCredits(id = id, apiKey = BuildConfig.API_KEY, language = Const.LANGUAGE)
            .map { response -> response.toActorCreditsInfo() }
    }
}