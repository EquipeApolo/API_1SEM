package com.cgmdigitalhouse.cinelist.home.repository

class MovieRepository {
    private val client = IMovieEndpoint.endpoint

    suspend fun getMoviesPopular() = client.getMoviesPopular()

    suspend fun getMoviesNowPaying() = client.getMoviesNowPaying()
}