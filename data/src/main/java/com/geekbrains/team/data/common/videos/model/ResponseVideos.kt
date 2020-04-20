package com.geekbrains.team.data.common.videos.model

import com.geekbrains.team.domain.base.model.Video
import com.google.gson.annotations.SerializedName


data class ResponseVideos(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<VideoDataModel>
) {
    data class VideoDataModel(
        @SerializedName("id")
        val id: String,
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("key")
        val key: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("site")
        val site: String,
        @SerializedName("size")
        val size: Int,
        @SerializedName("type")
        val type: String
    )
}

fun ResponseVideos.toVideo(): List<Video> =
    results.map { Video(it.key, it.site, it.type) }
