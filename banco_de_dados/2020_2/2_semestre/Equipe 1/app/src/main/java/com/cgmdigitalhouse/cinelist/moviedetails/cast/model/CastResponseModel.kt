package com.cgmdigitalhouse.cinelist.moviedetails.cast.model

import com.google.gson.annotations.SerializedName

data class CastResponseModel (
    @SerializedName("cast") val cast: List<CastModel>
)