package data.tv

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Series (
    @Expose
    val id: Int,

    @Expose
    @SerializedName("genre_ids")
    val genres: List<Int>,

    @Expose
    @SerializedName("original_name")
    val originalTitle: String,

    @Expose
    @SerializedName("name")
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
    @SerializedName("first_air_date")
    val realiseDate: String
)