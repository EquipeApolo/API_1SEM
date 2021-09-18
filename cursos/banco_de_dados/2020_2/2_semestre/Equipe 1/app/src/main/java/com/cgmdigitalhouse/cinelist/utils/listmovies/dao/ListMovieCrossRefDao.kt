package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
@Dao
interface ListMovieCrossRefDao {
    @Insert
    suspend fun inserirListMovieCrossRef(listMovieCrossRef: ListMovieCrossRefEntity):Long

    @Query("SELECT * FROM ListMovieCrossRefEntity WHERE listMovieId = :id")
    suspend fun obterlistsMoviestMovieCrossRef(id: Long): MutableList<ListMovieCrossRefEntity>

    @Query("DELETE FROM ListMovieCrossRefEntity WHERE listMovieId = :listId and movieId = :movieId")
    suspend fun removeMovieFromList(listId: Long, movieId: Int)
    @Query("SELECT COUNT(*) from ListMovieCrossRefEntity where movieId=:idMovie and listMovieId=:idListMovie")
    suspend fun checkIfMovieIsOnList(idMovie: Long, idListMovie: Long): List<String>

    @Query("SELECT *,(SELECT watchList FROM ListMovie where  ListMovie.listMovieId = ListMovieCrossRefEntity.listMovieId) as watchList," +
            "(SELECT useId FROM ListMovie where  ListMovie.listMovieId = ListMovieCrossRefEntity.listMovieId) as useId  FROM ListMovieCrossRefEntity " +
            "WHERE watchList = :watch AND useId = :idUser")
    suspend fun obterWatcListMoviestMovieCrossRef(watch: Int,idUser: String): MutableList<ListMovieCrossRefEntity>
}