package com.geekbrains.team.domain.movies.favoriteMovie.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository,
    private val movieDetailsRepository: MovieDetailsRepository
) : UseCase<List<Movie>, None> {
    override fun execute(params: None): Single<List<Movie>> =
        favoriteMoviesRepository.getFavoriteMoviesIds()
            .flatMapObservable { list ->
                Observable.fromIterable(list)
            }.flatMapSingle { item ->
                movieDetailsRepository.fetch(item)
            }
            .toList()
}