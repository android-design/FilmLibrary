package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.commonRepository.MovieCreditsRepository
import com.geekbrains.team.domain.movies.commonRepository.MovieImagesRepository
import com.geekbrains.team.domain.movies.model.*
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val detailsRepository: MovieDetailsRepository,
    private val creditsRepository: MovieCreditsRepository,
    private val imagesRepository: MovieImagesRepository
) :
    UseCase<Movie, GetMovieDetailsUseCase.Params> {

    override fun execute(params: Params): Single<Movie> = Single.zip(
        detailsRepository.fetch(params.id), creditsRepository.fetch(params.id),
        BiFunction { movie, credits -> getMovieDetails(movie, credits) })

    data class Params(val id: Int)

    private fun getMovieDetails(movie: Movie, credits: Credits): Movie {
        var director: String = ""
        var writers: String = ""
        var producer: String = ""
        credits.crew.forEach {
            when (it.name) {
                "Producer" -> producer += it.name + ", "
                "Story" -> writers += it.name + ","
                "Director" -> director += it.name + ", "
            }
        }

        director.dropLastWhile { !it.isLetter() }
        writers.dropLastWhile { !it.isLetter() }
        producer.dropLastWhile { !it.isLetter() }

        return movie.apply {
            this.director = director
            this.writer = writers
            this.producer = producer
            this.cast = credits.cast.map { it.toMovieActor() }
            this.crew = credits.crew.map { it.toMovieMember() }
        }

    }

    private fun getGenreString(genres: List<String>): String {

        var buffered: String = ""

        genres.forEach {
            buffered += "$it, "
        }

        return buffered.dropLastWhile { !it.isLetter() }
    }
}