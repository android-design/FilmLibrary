package com.geekbrains.team.domain.movies.topRatedMovies.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopRatedMoviesRepository
import com.geekbrains.team.domain.randomPage
import io.reactivex.Single
import javax.inject.Inject

class GetRandomTopRatedMovie @Inject constructor(private val repository: TopRatedMoviesRepository) :
    UseCase<Movie, None> {
    override fun execute(params: None): Single<Movie> =
        repository.fetch(randomPage())
            .flatMap { topRatedMovies -> Single.just(topRatedMovies.random()) }
}