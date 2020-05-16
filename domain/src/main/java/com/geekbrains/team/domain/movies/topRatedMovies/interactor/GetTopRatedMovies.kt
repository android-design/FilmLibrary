package com.geekbrains.team.domain.movies.topRatedMovies.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopRatedMoviesRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetTopRatedMovies @Inject constructor(private val repository: TopRatedMoviesRepository) :
    UseCase<MutableList<Movie>, None> {
    override fun execute(params: None): Single<MutableList<Movie>> =
        Observable.range(1, 10)
            .concatMapSingle { page -> repository.fetch(page) }
            .takeWhile { it.isNotEmpty() }
            .scan { t1: MutableList<Movie>, t2: MutableList<Movie> ->
                t1.addAll(t2)
                t1
            }
            .lastOrError()
}