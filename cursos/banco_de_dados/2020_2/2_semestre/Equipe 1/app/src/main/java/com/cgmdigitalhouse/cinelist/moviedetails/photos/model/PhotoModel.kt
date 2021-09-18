package com.cgmdigitalhouse.cinelist.moviedetails.photos.model

import com.google.gson.annotations.SerializedName


data class PhotoModel (
    @SerializedName("id") val id: Int,
    @SerializedName("backdrops") val posters: List<PosterModel>
)