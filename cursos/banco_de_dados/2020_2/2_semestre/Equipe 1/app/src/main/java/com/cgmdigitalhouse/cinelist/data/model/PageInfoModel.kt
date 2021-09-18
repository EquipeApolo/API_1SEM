package com.cgmdigitalhouse.cinelist.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class PageInfoModel (
    @SerializedName("total_results")
    val totalResultados: Int,
    @SerializedName("total_pages")
    val paginas: Int
)