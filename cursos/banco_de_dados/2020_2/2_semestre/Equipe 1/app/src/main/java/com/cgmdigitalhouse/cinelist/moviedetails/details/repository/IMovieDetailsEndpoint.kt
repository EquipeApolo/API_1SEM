package com.cgmdigitalhouse.cinelist.moviedetails.details.repository

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieDetailsEndpoint {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR"
    ): MovieModel


        companion object {
        const val movieId = 550
        val endpoint: IMovieDetailsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMovieDetailsEndpoint::class.java)
        }
    }
}