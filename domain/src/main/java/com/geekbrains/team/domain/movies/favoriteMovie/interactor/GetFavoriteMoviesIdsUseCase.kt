package com.geekbrains.team.domain.movies.favoriteMovie.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteMoviesIdsUseCase @Inject constructor(private val repository: FavoriteMoviesRepository) :
    UseCase<List<Int>, None> {
    override fun execute(params: None): Single<out List<Int>> = repository.getFavoriteMoviesIds()
}