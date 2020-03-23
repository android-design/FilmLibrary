package data.repositories

import com.geekbrains.team.domain.model.Movie
import com.geekbrains.team.domain.repository.TopMoviesRepository
import data.movie.TopRatedMovies
import data.retrofit.TopMoviesApi
import io.reactivex.Single
import io.reactivex.functions.Function

class TopMoviesRepositoryImpl(val topMoviesApi: TopMoviesApi): TopMoviesRepository {

    override fun getTopMovies(language: String): Single<List<Movie>> {
        return topMoviesApi.getTopMovies(language).map { object: Function<TopRatedMovies, Movie> {
            override fun apply(t: TopRatedMovies): Movie {
                return
            }

        } }
    }
}