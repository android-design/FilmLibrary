package com.geekbrains.team.data.common.images.model

import com.geekbrains.team.data.Const.POSTER_AND_BACKDROP_PREFIX
import com.geekbrains.team.domain.base.model.Images
import com.google.gson.annotations.SerializedName


data class ResponseImages(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrops")
    val backdrops: List<BackdropDataModel>,
    @SerializedName("posters")
    val posters: List<PosterDataModel>
) {
    data class BackdropDataModel(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("iso_639_1")
        val iso6391: String?,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("width")
        val width: Int
    )

    data class PosterDataModel(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("width")
        val width: Int
    )
}

fun ResponseImages.toImages(): Images =
    Images(
        backdrops.take(15).map { Images.Backdrop(POSTER_AND_BACKDROP_PREFIX + it.filePath) },
        posters.take(15).map { Images.Poster(POSTER_AND_BACKDROP_PREFIX + it.filePath) }
    )
