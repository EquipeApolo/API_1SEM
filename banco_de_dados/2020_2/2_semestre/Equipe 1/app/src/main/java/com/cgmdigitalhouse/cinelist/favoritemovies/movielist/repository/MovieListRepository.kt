package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository

import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

class MovieListRepository(private val movieListDao: ListMovieDao) {
    suspend fun inserirListMovie(listMovieEntity: ListMovieEntity) = movieListDao.inserirListMovie(listMovieEntity)
    suspend fun getListMovies(idUser:String): MutableList<MovieListModel> = movieListDao.obterlistsMovies(idUser)
    suspend fun getAllMovieLists(idUser:String): MutableList<ListMovieEntity> = movieListDao.getAllMovieLists(idUser)
    suspend fun searchWatchList(idUser:String) = movieListDao.searchWatchList(idUser)
//    suspend fun getListMovies() = movieListDao.obterListsMovies()
//    suspend fun getMovies() = movieListDao.obterMovies()
}
