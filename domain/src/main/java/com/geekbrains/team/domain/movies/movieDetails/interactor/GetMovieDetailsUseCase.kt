package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository) :
    UseCase<Movie, GetMovieDetailsUseCase.Params> {

    override fun execute(params: Params): Single<Movie> = repository.fetch(id = params.id)

    data class Params(val id: Int)
}