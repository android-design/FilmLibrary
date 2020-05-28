package com.geekbrains.team.data.tv.credits.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.tv.credits.service.TVCreditsApi
import com.geekbrains.team.data.tv.credits.service.model.toCredits
import com.geekbrains.team.domain.base.model.Credits
import com.geekbrains.team.domain.tv.credits.repository.TVCreditsRepository
import io.reactivex.Single
import javax.inject.Inject

class TVCreditsRepositoryImpl @Inject() constructor(private  val api: TVCreditsApi):
    TVCreditsRepository {
    override fun fetch(id: Int): Single<Credits> {
        return api.getMovieCredits(movieId = id, apiKey = BuildConfig.API_KEY).map { it.toCredits() }
    }
}