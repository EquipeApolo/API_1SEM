package com.cgmdigitalhouse.cinelist.utils.listmovies.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "ListMovie")
data class ListMovieEntity (
    @PrimaryKey(autoGenerate = true)
    var listMovieId: Long,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var imageURL: String,
    @ColumnInfo
    var useId: String,
    @ColumnInfo
    var watchList: Boolean
)