package com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.repository.WatchlistRepository
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import kotlinx.coroutines.Dispatchers

class WatchlistViewModel(
    private val repository: WatchlistRepository
) : ViewModel() {
    val movies = MutableLiveData<MutableList<MovieModelOffline>>()


    fun getMovies(idUser:String) = liveData(Dispatchers.IO) {
        emit(repository.getWatchList(idUser))
    }

    fun removeMovieFromList(listMovieId: Long, movieId: Int) =  liveData(Dispatchers.IO) {
        emit(repository.removeMovieFromList(listMovieId, movieId))
    }

    fun addMovieToList(listMovieId: Long, movieId: Int) =  liveData(Dispatchers.IO) {
        emit(repository.addMovieToList(listMovieId, movieId))
    }
    class WatchlistViewModelFactory(
        private val repository: WatchlistRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WatchlistViewModel(repository) as T
        }

    }
}