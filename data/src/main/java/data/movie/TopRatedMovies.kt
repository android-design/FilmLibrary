package data.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import data.movie.Movie

data class TopRatedMovies(
    @Expose
    val result: List<Movie>,

    @Expose
    val page: Int,

    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,

    @Expose
    @SerializedName("total_result")
    val totalResult: Int
)