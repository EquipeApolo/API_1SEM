package com.cgmdigitalhouse.cinelist.utils.listmovies.entity

import androidx.room.Entity

@Entity(primaryKeys = ["listMovieId", "movieId"])
data class ListMovieCrossRefEntity (
    val listMovieId: Long,
    val movieId: Long
    )