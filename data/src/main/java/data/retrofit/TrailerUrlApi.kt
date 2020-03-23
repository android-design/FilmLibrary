package data.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TrailerUrlApi {
    @GET
    fun getTrailerUrl(@Path("id") id: Int): Single<String>
}