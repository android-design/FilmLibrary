package data.movie



import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import data.movie.Movie

data class UpcomingMovies(

    @Expose
    val result: List<Movie>,

    @Expose
    val page: Int,

    @Expose
    @SerializedName("total_result")
    val totalResult: Int,

    @Expose
    @SerializedName("totalPages")
    val totalPages: Int,

    @Expose
    val dates: List<String>

)