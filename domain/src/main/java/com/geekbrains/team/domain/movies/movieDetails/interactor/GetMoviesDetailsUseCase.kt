package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.UseCaseFlowable
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository): UseCaseFlowable<List<Movie>, List<Int>> {

    override fun execute(params: List<Int>): Flowable<List<Movie>> =
        repository.getMovieList(params)

}