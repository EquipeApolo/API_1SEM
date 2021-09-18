package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model

data class MovieListModel(
    val listMovieId: Long,
    val name: String,
    val description: String,
    val qtd: Int,
    val imageURL: String
)

