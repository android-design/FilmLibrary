package com.geekbrains.team.data.movies.movieCreadits.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.movieCreadits.service.MovieCreditsApi
import com.geekbrains.team.domain.movies.model.Credits
import com.geekbrains.team.domain.movies.movieCredits.repository.MovieCreditsRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieCreditsRepositoryImpl @Inject constructor(private val api: MovieCreditsApi): MovieCreditsRepository {
    override fun fetch(id: Int): Single<Credits> {
        api.getMovieCredits(BuildConfig.API_KEY, Const.LANGUAGE, id)
    }


}