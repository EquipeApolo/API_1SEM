package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository.MovieListRepository
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import kotlinx.coroutines.Dispatchers

class MovieListViewModel(
    private val repository: MovieListRepository
) : ViewModel() {
    fun inserirListMovie(nome: String, descricao: String,imageURL:String,idUse:String,warchList:Boolean) = liveData(Dispatchers.IO) {
        val listMovie = ListMovieEntity(0, nome, descricao,imageURL,idUse,warchList)
        val newId = repository.inserirListMovie(ListMovieEntity(0, nome, descricao,imageURL,idUse,warchList))

        listMovie.listMovieId = newId

        emit(listMovie)
    }

    fun getMovieLists(idUser:String) = liveData(Dispatchers.IO) {
        emit(repository.getListMovies(idUser))
    }

    fun getAllMovieLists(idUser:String)  = liveData(Dispatchers.IO) {
        emit(repository.getAllMovieLists(idUser))
    }

    fun searchWatchList(idUser:String)= liveData(Dispatchers.IO) {
        emit(repository.searchWatchList(idUser))
    }

    @Suppress("UNCHECKED_CAST")
    class MovieListViewModelFactory(
        private val repository: MovieListRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListViewModel(repository) as T
        }

    }
}