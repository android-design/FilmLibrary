package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.commonRepository.MoviesImagesRepository
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.model.fillMovieGenres
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Single
import io.reactivex.functions.Function4
import javax.inject.Inject
import javax.inject.Named

class GetMovieDetailsUseCase @Inject constructor(
    private val detailsRepository: MovieDetailsRepository,
    private val moviesImagesRepository: MoviesImagesRepository,
    private val moviesGenresRepository: MoviesGenresRepository,
    @param:Named("MovieVideos") private val moviesVideoRepository: VideosRepository
) :
    UseCase<Movie, GetMovieDetailsUseCase.Params> {

    override fun execute(params: Params): Single<Movie> =
        Single.zip(
            detailsRepository.fetch(params.id),
            moviesImagesRepository.fetch(params.id),
            moviesGenresRepository.fetch(),
            moviesVideoRepository.fetch(params.id),
            Function4 { sourceMovie, sourceImages, listGenres, videos ->
                val movie = fillMovieGenres(listGenres, sourceMovie)
                movie.images = sourceImages
                movie.videos = videos
                movie
            }
        )

    data class Params(val id: Int)
}

//    private fun getMovieDetails(movie: Movie, credits: Credits): Movie {
//        var director: String = ""
//        var writers: String = ""
//        var producer: String = ""
//        credits.crew.forEach {
//            when (it.name) {
//                "Producer" -> producer += it.name + ", "
//                "Story" -> writers += it.name + ","
//                "Director" -> director += it.name + ", "
//            }
//        }
//
//        director.dropLastWhile { !it.isLetter() }
//        writers.dropLastWhile { !it.isLetter() }
//        producer.dropLastWhile { !it.isLetter() }
//
//        return movie.apply {
//            this.director = director
//            this.writer = writers
//            this.producer = producer
//            this.cast = credits.cast.map { it.toMovieActor() }
//            this.crew = credits.crew.map { it.toMovieMember() }
//        }
//
//    }
//
//    private fun getGenreString(genres: List<String>): String {
//
//        var buffered: String = ""
//
//        genres.forEach {
//            buffered += "$it, "
//        }
//
//        return buffered.dropLastWhile { !it.isLetter() }
//    }
