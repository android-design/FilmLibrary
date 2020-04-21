package com.geekbrains.team.data.movies.movieImages.service.model

import com.google.gson.annotations.SerializedName

data class MovieImagesResponse(
    val id: Int,
    val backdrops: List<Backdrop>,
    val posters: List<Poster>
) {
    data class Backdrop(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String,
        val height: Int,
        val width: Int
    ) {
        override fun toString(): String {
            return filePath
        }
    }

    data class Poster(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String,
        val height: Int,
        val width: Int
    ) {
        override fun toString(): String {
            return filePath
        }
    }
}

fun MovieImagesResponse.toPostersList(): List<String> =
    posters.map { poster -> poster.toString() }

fun MovieImagesResponse.toBackdropsList(): List<String> =
    backdrops.map { backdrop -> backdrop.toString() }