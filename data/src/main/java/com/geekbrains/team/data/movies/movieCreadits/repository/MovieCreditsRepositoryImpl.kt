package com.geekbrains.team.data.movies.movieCreadits.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.movies.movieCreadits.service.MovieCreditsApi
import com.geekbrains.team.data.movies.movieCreadits.service.model.toCredits
import com.geekbrains.team.domain.movies.commonRepository.MovieCreditsRepository
import com.geekbrains.team.domain.base.model.Credits
import io.reactivex.Single
import javax.inject.Inject

class MovieCreditsRepositoryImpl @Inject constructor(private val api: MovieCreditsApi) :
    MovieCreditsRepository {
    override fun fetch(id: Int): Single<Credits> {
        return api.getMovieCredits(
            movieId = id,
            apiKey = BuildConfig.API_KEY
        ).map { it.toCredits() }
    }
}