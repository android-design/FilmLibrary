package com.geekbrains.team.domain.movies.nowPlayingMovies.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.nowPlayingMovies.repository.NowPlayingMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFirstNowPlayingMovie @Inject constructor(private val repository: NowPlayingMoviesRepository) :
    UseCase<Movie, None> {
    override fun execute(params: None): Single<Movie> =
        repository.fetch()
            .flatMap { movies -> Single.just(movies.first()) }
}