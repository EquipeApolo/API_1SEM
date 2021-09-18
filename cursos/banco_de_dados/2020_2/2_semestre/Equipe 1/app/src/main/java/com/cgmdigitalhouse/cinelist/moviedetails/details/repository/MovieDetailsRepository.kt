package com.cgmdigitalhouse.cinelist.moviedetails.details.repository

import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieCrossRefDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity


class MovieDetailsRepository() {
    lateinit var listMovieCrossRefDao: ListMovieCrossRefDao
    private val client = IMovieDetailsEndpoint.endpoint

    suspend fun getMovieDetails(id: Int) = client.getMovieDetails(id)
    suspend fun inserirListMovieCrossRef(listMovieCrossRef: ListMovieCrossRefEntity):Long = listMovieCrossRefDao!!.inserirListMovieCrossRef(listMovieCrossRef)
    suspend fun checkIfMovieIsOnList(idMovie: Long, idListMovie: Long) = listMovieCrossRefDao!!.checkIfMovieIsOnList(idMovie, idListMovie)
}