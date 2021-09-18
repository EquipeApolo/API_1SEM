package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.*
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

@Dao
interface ListMovieDao {
    @Insert
    suspend fun inserirListMovie(listMovieEntity: ListMovieEntity): Long
  
    @Query("SELECT listMovieId, name, description, (SELECT COUNT(*) FROM ListMovieCrossRefEntity where ListMovieCrossRefEntity.listMovieId = ListMovie.listMovieId) as qtd, imageURL FROM ListMovie WHERE  useId = :idUser AND watchList = 0")
    suspend fun obterlistsMovies(idUser:String): MutableList<MovieListModel>

    @Query("SELECT * FROM ListMovie WHERE useId = :idUser")
    suspend fun getAllMovieLists(idUser:String): MutableList<ListMovieEntity>
  
    @Query("SELECT * FROM ListMovie WHERE listMovieId= :id and useId = :idUser")
    suspend fun findList(id: Long,idUser:String): List<ListMovieEntity>

    @Query("SELECT COUNT(*) FROM ListMovie where watchList = 1 AND useId = :idUser")
    suspend fun searchWatchList(idUser:String) :List<String>

    @Update
    suspend fun editList(listMovieEntity: ListMovieEntity)

    @Query("DELETE FROM ListMovie WHERE listMovieId= :id")
    suspend fun deleteListById(id: Long)
}
