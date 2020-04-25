package com.geekbrains.team.domain.movies.movieDetails.interactor

import android.util.Log
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.commonRepository.MovieCreditsRepository
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.commonRepository.MoviesImagesRepository
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import com.geekbrains.team.domain.movies.model.*
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import io.reactivex.functions.Function4
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

    override fun execute(params: Params): Single<Movie> =
        Single.zip(
            detailsRepository.fetch(params.id),
            moviesImagesRepository.fetch(params.id),
            moviesGenresRepository.fetch(),
            moviesVideoRepository.fetch(params.id),
            movieCreditsRepository.fetch(params.id),
            Function5 { sourceMovie, sourceImages, listGenres, videos, credits ->
                val movie = fillMovieGenres(listGenres, sourceMovie)
                movie.images = sourceImages
                movie.videos = videos
                getMovieDetails(movie, credits)
                movie
            }
        )

    data class Params(val id: Int)
}

    private fun getMovieDetails(movie: Movie, credits: Credits): Movie {
        Log.d("GetMovieDetailsUseCase", "getMovieDetails()" + credits.cast?.get(0)?.name.toString())
        var director: String = ""
        var writers: String = ""
        var producer: String = ""
        credits.crew?.forEach {
            when (it.job) {
                "Producer" -> producer += it.name + ", "
                "Director" -> director += it.name + ", "
            }

            when(it.department) {
                "Writing" -> writers += it.name + ", "
            }
        }

        director.dropLast(3)
        producer.dropLast(3)
        writers.dropLast(3)

        return movie.apply {
            this.director = director
            this.writer = writers
            this.producer = producer
            this.cast = credits.cast?.map { it.toMovieActor() }
            this.crew = credits.crew?.map { it.toMovieMember() }
        }

    }
