package com.geekbrains.team.data.di

import com.geekbrains.team.data.Const.BASE_URL
import com.geekbrains.team.data.movies.detailsMovies.service.MovieDetailsApi
import com.geekbrains.team.data.movies.genresMovies.service.GenresMoviesApi
import com.geekbrains.team.data.movies.genresMovies.service.GenresTVApi
import com.geekbrains.team.data.movies.imagesMovies.service.ImagesMoviesApi
import com.geekbrains.team.data.movies.nowPlayingMovies.service.NowPlayingMoviesApi
import com.geekbrains.team.data.movies.searchMovies.service.SearchMoviesApi
import com.geekbrains.team.data.movies.searchMovies.service.SearchTVApi
import com.geekbrains.team.data.movies.topRatedMovies.service.TopRatedMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.UpcomingMoviesApi
import com.geekbrains.team.data.movies.videosMovies.service.VideosMoviesApi
import com.geekbrains.team.data.tv.nowPlayingTV.service.NowPlayingTVApi
import com.geekbrains.team.data.tv.topRatedTV.service.TopRatedTVApi
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
    fun getNowPlayingTVApi(retrofit: Retrofit): NowPlayingTVApi =
        retrofit.create(NowPlayingTVApi::class.java)

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

    @Provides
    fun getTopRatedTVApi(retrofit: Retrofit): TopRatedTVApi =
        retrofit.create(TopRatedTVApi::class.java)

    @Provides
    fun getImagesMoviesApi(retrofit: Retrofit): ImagesMoviesApi =
        retrofit.create(ImagesMoviesApi::class.java)

    @Provides
    fun getVideosMoviesApi(retrofit: Retrofit): VideosMoviesApi =
        retrofit.create(VideosMoviesApi::class.java)
}