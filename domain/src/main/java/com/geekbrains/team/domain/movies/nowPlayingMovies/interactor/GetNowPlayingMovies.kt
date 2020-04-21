package com.geekbrains.team.domain.movies.nowPlayingMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.model.fillMoviesGenres
import com.geekbrains.team.domain.movies.nowPlayingMovies.repository.NowPlayingMoviesRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetNowPlayingMovies @Inject constructor(
    private val nowPlayingMoviesRepository: NowPlayingMoviesRepository,
    private val repositoryMoviesGenres: MoviesGenresRepository
) :
    UseCase<List<Movie>, GetNowPlayingMovies.Params> {
    override fun execute(params: Params): Single<List<Movie>> =
        Single.zip(
            repositoryMoviesGenres.fetch(),
            nowPlayingMoviesRepository.fetch(page = params.page),
            BiFunction { moviesGenres, movies ->
                fillMoviesGenres(moviesGenres, movies)
            })


    data class Params(val page: Int)
}