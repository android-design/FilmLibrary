package data.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopRatedMovies(
    @Expose
    val result: List<MovieModel>,

    @Expose
    val page: Int,

    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,

    @Expose
    @SerializedName("total_result")
    val totalResult: Int
)