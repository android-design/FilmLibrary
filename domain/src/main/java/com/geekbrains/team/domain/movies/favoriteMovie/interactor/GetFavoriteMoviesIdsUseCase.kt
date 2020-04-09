package com.geekbrains.team.domain.movies.favoriteMovie.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.UseCaseWithoutParameters
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteMoviesIdsUseCase @Inject constructor(private val repository: FavoriteMoviesRepository):
    UseCaseWithoutParameters<List<Int>> {
    override fun execute(): Single<List<Int>> = repository.getFavoriteMoviesIds()
}