package data.tv

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopRatesSeries(
    @Expose
    val result: List<Series>,

    @Expose
    val page: Int,

    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,

    @Expose
    @SerializedName("total_result")
    val totalResult: Int
)