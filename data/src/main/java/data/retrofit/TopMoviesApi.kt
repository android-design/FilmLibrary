package data.retrofit

import data.movie.TopRatedMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TopMoviesApi {
    @GET()
    fun getTopMovies(@Path("language") language: String): Single<TopRatedMovies>
}