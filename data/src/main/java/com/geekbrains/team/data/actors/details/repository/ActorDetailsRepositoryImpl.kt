package com.geekbrains.team.data.actors.details.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.actors.credits.service.model.toActorCreditsInfo
import com.geekbrains.team.data.actors.details.service.ActorDetailsApi
import com.geekbrains.team.data.actors.details.service.model.toActorInformation
import com.geekbrains.team.domain.actors.details.repository.ActorDetailsRepository
import com.geekbrains.team.domain.actors.model.ActorInformation
import io.reactivex.Single
import javax.inject.Inject

class ActorDetailsRepositoryImpl @Inject constructor(
    private val api: ActorDetailsApi
): ActorDetailsRepository {
    override fun fetch(id: Int): Single<ActorInformation> {
        return api.getActorDetails(id = id, apiKey = BuildConfig.API_KEY, language = Const.LANGUAGE)
            .map { response -> response.toActorInformation() }
    }
}