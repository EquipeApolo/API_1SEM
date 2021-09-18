package com.cgmdigitalhouse.cinelist.moviedetails.photos.repository

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.moviedetails.photos.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IPhotosEndpoint {
    @GET("movie/{movieId}/images")
    suspend fun getPhotos(
        @Path("movieId") movieId: Int): PhotoModel

    companion object {
        val endpoint: IPhotosEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IPhotosEndpoint::class.java)
        }
    }
}