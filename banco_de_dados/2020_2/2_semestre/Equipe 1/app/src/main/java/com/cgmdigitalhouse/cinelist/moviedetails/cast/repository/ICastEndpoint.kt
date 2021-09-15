package com.cgmdigitalhouse.cinelist.moviedetails.cast.repository

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.moviedetails.cast.model.CastResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ICastEndpoint {
    @GET("movie/{movieId}/credits")
    suspend fun getCast(
        @Path("movieId") movieId: Int): CastResponseModel

    companion object {
        val endpoint: ICastEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(ICastEndpoint::class.java)
        }
    }
}