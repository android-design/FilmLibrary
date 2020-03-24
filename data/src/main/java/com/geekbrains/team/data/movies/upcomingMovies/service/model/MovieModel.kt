package com.geekbrains.team.data.movies.upcomingMovies.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieModel (

    val id: Int,

    val genres: List<Int>,

    @SerializedName("original_title")
    val originalTitle: String,

    val title: String,

    val overview: String,

    @SerializedName("backdrop_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: String,

    @SerializedName("release_date")
    val realiseDate: String
)
