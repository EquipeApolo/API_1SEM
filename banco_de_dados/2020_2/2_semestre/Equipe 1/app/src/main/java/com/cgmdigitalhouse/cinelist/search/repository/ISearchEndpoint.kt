package com.cgmdigitalhouse.cinelist.search.repository

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.data.model.ResponseModel
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchEndpoint {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pt-BR"
    ): ResponseModel<MovieModel>

    companion object {
        val endpoint: ISearchEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(ISearchEndpoint::class.java)
        }
    }
}