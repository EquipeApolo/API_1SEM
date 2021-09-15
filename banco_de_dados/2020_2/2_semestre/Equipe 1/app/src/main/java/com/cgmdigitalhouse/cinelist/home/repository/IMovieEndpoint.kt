package com.cgmdigitalhouse.cinelist.home.repository

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.data.model.ResponseModel
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieEndpoint {
    @GET("movie/popular")
    suspend fun getMoviesPopular(@Query("language") language: String = "pt-BR"): ResponseModel<MovieModel>

    @GET("movie/now_playing")
    suspend fun getMoviesNowPaying(@Query("language") language: String = "pt-BR"): ResponseModel<MovieModel>

    companion object {
        val endpoint: IMovieEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMovieEndpoint::class.java)
        }
    }
}