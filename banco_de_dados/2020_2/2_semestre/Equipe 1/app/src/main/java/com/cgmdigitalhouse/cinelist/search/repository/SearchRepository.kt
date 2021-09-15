package com.cgmdigitalhouse.cinelist.search.repository

class SearchRepository {
    private val client = ISearchEndpoint.endpoint

    suspend fun searchMovies(query: String, page: Int = 1) = client.searchMovies(query, page)
}