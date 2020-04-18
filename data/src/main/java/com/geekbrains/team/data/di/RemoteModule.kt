package com.geekbrains.team.data.di

import com.geekbrains.team.data.Const.BASE_URL
import com.geekbrains.team.data.movies.genresMovies.service.GenresMoviesApi
import com.geekbrains.team.data.movies.genresMovies.service.GenresTVApi
import com.geekbrains.team.data.movies.movieDetails.service.MovieDetailsApi
import com.geekbrains.team.data.movies.nowPlayingMovies.service.NowPlayingMoviesApi
import com.geekbrains.team.data.movies.searchMovies.service.SearchMoviesApi
import com.geekbrains.team.data.movies.searchMovies.service.SearchTVApi
import com.geekbrains.team.data.movies.topRatedMovies.service.TopRatedMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.UpcomingMoviesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Dagger module for connection instances.
 */
@Module
class RemoteModule {

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun getMovieDetailsApi(retrofit: Retrofit): MovieDetailsApi =
        retrofit.create(MovieDetailsApi::class.java)

    @Provides
    fun getNowPlayingMoviesApi(retrofit: Retrofit): NowPlayingMoviesApi =
        retrofit.create(NowPlayingMoviesApi::class.java)

    @Provides
    fun getUpcomingMoviesApi(retrofit: Retrofit): UpcomingMoviesApi =
        retrofit.create(UpcomingMoviesApi::class.java)

    @Provides
    fun getTopRatedMoviesApi(retrofit: Retrofit): TopRatedMoviesApi =
        retrofit.create(TopRatedMoviesApi::class.java)

    @Provides
    fun getSearchMoviesApi(retrofit: Retrofit): SearchMoviesApi =
        retrofit.create(SearchMoviesApi::class.java)

    @Provides
    fun getSearchTVShowApi(retrofit: Retrofit): SearchTVApi =
        retrofit.create(SearchTVApi::class.java)

    @Provides
    fun getGenresMoviesApi(retrofit: Retrofit): GenresMoviesApi =
        retrofit.create(GenresMoviesApi::class.java)

    @Provides
    fun getGenresTVApi(retrofit: Retrofit): GenresTVApi =
        retrofit.create(GenresTVApi::class.java)
}