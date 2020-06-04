package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.model.Credits
import com.geekbrains.team.domain.base.model.toMovieActor
import com.geekbrains.team.domain.base.model.toMovieMember
import com.geekbrains.team.domain.movies.commonRepository.MovieCreditsRepository
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.commonRepository.MoviesImagesRepository
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import com.geekbrains.team.domain.movies.model.*
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase.Companion.DIRECTOR
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase.Companion.ELEMENTS_TO_TAKE
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase.Companion.PRODUCER
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase.Companion.WRITING
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import io.reactivex.functions.Function5
import javax.inject.Inject
import javax.inject.Named

class GetMovieDetailsUseCase @Inject constructor(
    private val detailsRepository: MovieDetailsRepository,
    private val moviesImagesRepository: MoviesImagesRepository,
    private val moviesGenresRepository: MoviesGenresRepository,
    private val movieCreditsRepository: MovieCreditsRepository,
    @param:Named("MovieVideos") private val moviesVideoRepository: VideosRepository
) :
    UseCase<Movie, GetMovieDetailsUseCase.Params> {

    companion object {
        const val DIRECTOR = "DIRECTOR"
        const val WRITING = "WRITING"
        const val PRODUCER = "PRODUCER"
        const val ELEMENTS_TO_TAKE = 3
    }

    override fun execute(params: Params): Single<Movie> =
        Single.zip(
            detailsRepository.fetch(params.id),
            moviesImagesRepository.fetch(params.id),
            moviesGenresRepository.fetch(),
            moviesVideoRepository.fetch(params.id),
            movieCreditsRepository.fetch(params.id),
            Function5 { sourceMovie, sourceImages, listGenres, videos, credits ->
                fillMovieGenres(listGenres, sourceMovie)
                fillMovieCredits(sourceMovie, credits)
                sourceMovie.images = sourceImages
                sourceMovie.videos = videos

                sourceMovie
            }
        )

    data class Params(val id: Int)
}

private fun fillMovieCredits(movie: Movie, credits: Credits) {
    movie.apply {
        director = personsByJob(credits, DIRECTOR)
        writer = personsByDepartments(credits, WRITING)
        producer = personsByJob(credits, PRODUCER)
        cast = credits.cast?.map { it.toMovieActor() }
        crew = credits.crew?.map { it.toMovieMember() }
    }
}

private fun personsByJob(credits: Credits, jobToFilter: String) =
    credits.crew?.filter { persons -> persons.job.equals(jobToFilter, true) }
        ?.take(ELEMENTS_TO_TAKE)
        ?.joinToString { it.name }

private fun personsByDepartments(credits: Credits, departmentToFilter: String) =
    credits.crew?.filter { persons -> persons.department.equals(departmentToFilter, true) }
        ?.take(ELEMENTS_TO_TAKE)
        ?.joinToString { it.name }