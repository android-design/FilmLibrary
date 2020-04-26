package com.geekbrains.team.domain.movies.similarMovie.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.similarMovie.repository.SimilarMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(private val repository: SimilarMoviesRepository):
    UseCase<List<Movie>, GetSimilarMoviesUseCase.Param>
{

    override fun execute(params: Param): Single<out List<Movie>> {
        return repository.fetch(id = params.id, page = params.page)
    }

    data class Param(val id: Int, val page: Int)
}