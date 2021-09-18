package com.cgmdigitalhouse.cinelist.moviedetails.photos.model

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.google.gson.annotations.SerializedName

data class PosterModel (
    @SerializedName("file_path") val filePath: String
        ){
    fun getPathPoster():String{
        return NetworkUtils.BASE_URL_IMAGE+filePath
    }
}