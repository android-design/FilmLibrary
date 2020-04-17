package com.geekbrains.team.data.movies.movieDetails.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.movieDetails.service.MovieDetailsApi
import com.geekbrains.team.data.movies.movieDetails.service.model.toMovie
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(private val api: MovieDetailsApi) :
    MovieDetailsRepository {
    override fun fetch(id: Int): Single<Movie> {
        return api.getMovieDetails(BuildConfig.API_KEY, Const.LANGUAGE, id)
            .map { movieDetailsResponse -> movieDetailsResponse.toMovie() }
    }
}