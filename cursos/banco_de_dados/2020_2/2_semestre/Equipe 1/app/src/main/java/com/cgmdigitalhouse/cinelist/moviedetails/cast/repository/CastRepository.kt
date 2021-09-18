package com.cgmdigitalhouse.cinelist.moviedetails.cast.repository

class CastRepository{
    private val client = ICastEndpoint.endpoint

    suspend fun getCast(id: Int) = client.getCast(id)
}