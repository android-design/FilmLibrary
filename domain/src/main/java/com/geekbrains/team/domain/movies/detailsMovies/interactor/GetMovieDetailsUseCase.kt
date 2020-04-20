package com.geekbrains.team.domain.movies.detailsMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.commonRepository.MoviesImagesRepository
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import com.geekbrains.team.domain.movies.detailsMovies.repository.MovieDetailsRepository
import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val moviesImagesRepository: MoviesImagesRepository,
    private val moviesVideoRepository: VideosRepository
) :
    UseCase<Movie, GetMovieDetailsUseCase.Params> {

    override fun execute(params: Params): Single<Movie> =
        Single.zip(
            movieDetailsRepository.fetch(id = params.id),
            moviesImagesRepository.fetch(id = params.id),
            moviesVideoRepository.fetch(id = params.id),
            Function3 { movie, images, videos ->
                movie.imagesNew = images
                movie.videosNew = videos
                movie
            }
        )

    data class Params(val id: Int)
}