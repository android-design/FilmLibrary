package data.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieModel (
    @Expose
    val id: Int,

    @Expose
    val genres: List<Int>,

    @Expose
    @SerializedName("original_title")
    val originalTitle: String,

    @Expose
    val title: String,

    @Expose
    val overview: String,
    @Expose
    @SerializedName("backdrop_path")
    val posterPath: String,

    @Expose
    @SerializedName("vote_average")
    val voteAverage: String,

    @Expose
    @SerializedName("release_date")
    val realiseDate: String
)
