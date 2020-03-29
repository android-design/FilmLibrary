package com.geekbrains.team.domain.movies.topRatedMovies.interactor

import com.geekbrains.team.domain.base.SingleUseCase
import com.geekbrains.team.domain.movies.topRatedMovies.model.TopRatedMovie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopMoviesRepository
import io.reactivex.Single

class GetTopRatedMovies(private val repository: TopMoviesRepository):
    SingleUseCase<List<TopRatedMovie>, Int> {
    override fun execute(params: Int): Single<List<TopRatedMovie>> {
        return repository.fetch(page = params)
    }
}