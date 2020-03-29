package com.geekbrains.team.domain.movies.upcomingMovies.interactor

import com.geekbrains.team.domain.base.SingleUseCase
import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import com.geekbrains.team.domain.movies.upcomingMovies.repository.UpcomingMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUpcomingMovies @Inject constructor(private val repository: UpcomingMoviesRepository) :
    SingleUseCase<List<UpcomingMovie>, Int> {
    override fun execute(params: Int): Single<List<UpcomingMovie>> {
        return repository.fetch(page = params)
    }
}