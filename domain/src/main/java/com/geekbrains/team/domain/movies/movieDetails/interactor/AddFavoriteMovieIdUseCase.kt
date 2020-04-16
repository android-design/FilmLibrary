package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCaseCompletable
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddFavoriteMovieIdUseCase @Inject constructor(
    private val repository: FavoriteMoviesRepository):
    UseCaseCompletable<Int> {

    override fun execute(params: Int): Completable {
        return repository.addFavoriteMovieId(params)
    }

}