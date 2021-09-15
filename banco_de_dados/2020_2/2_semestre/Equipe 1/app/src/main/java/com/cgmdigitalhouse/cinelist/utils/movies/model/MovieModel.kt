package com.cgmdigitalhouse.cinelist.utils.movies.model

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.moviedetails.details.model.GenreModel
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("genres") val genres: List<GenreModel>,
    @SerializedName("overview") val overview: String,
    @SerializedName("runtime") val runtime: Int
){
    fun getPathPoster():String{
        return NetworkUtils.BASE_URL_IMAGE+posterPath
    }
}