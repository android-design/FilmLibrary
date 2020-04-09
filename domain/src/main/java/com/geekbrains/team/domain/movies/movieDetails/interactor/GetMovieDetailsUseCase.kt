package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository):
    UseCase<Movie, Int> {

    override fun execute(params: Int): Single<Movie> = repository.getMovie(params)

}