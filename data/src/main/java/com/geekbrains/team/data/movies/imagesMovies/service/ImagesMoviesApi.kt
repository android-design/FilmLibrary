package com.geekbrains.team.data.movies.imagesMovies.service

import com.geekbrains.team.data.common.images.model.ResponseImages
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImagesMoviesApi {
    @GET("/3/movie/{id}/images?")
    fun getMovieImages(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Single<ResponseImages>
}