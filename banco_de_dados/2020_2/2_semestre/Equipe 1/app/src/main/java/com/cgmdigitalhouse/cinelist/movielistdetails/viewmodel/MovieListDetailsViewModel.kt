package com.cgmdigitalhouse.cinelist.movielistdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.movielistdetails.repository.MovieListDetailsRepository
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import kotlinx.coroutines.Dispatchers

class MovieListDetailsViewModel(
    private val repository: MovieListDetailsRepository
) : ViewModel() {

    fun getListMoviesCrossRefEntity(id:Long)= liveData(Dispatchers.IO) {
        emit(repository.getListMoviesCrossRefEntity(id))
    }

    fun getListDetais(id: Long,idUse:String) = liveData(Dispatchers.IO) {
        emit(repository.findList(id,idUse))
    }

    fun editList(id: Long, name: String, description: String, imageURL:String,idUse:String,warchList:Boolean) =  liveData(Dispatchers.IO) {
        emit(repository.editList(ListMovieEntity(id, name, description,imageURL,idUse,warchList)))
    }

    fun deleteList(id: Long) =  liveData(Dispatchers.IO) {
        emit(repository.deleteListById(id))
    }

    fun removeMovieFromList(listId: Long, movieId: Int) =  liveData(Dispatchers.IO) {
        emit(repository.removeMovieFromList(listId, movieId))
    }

    fun addMovieToList(listId: Long, movieId: Int)=  liveData(Dispatchers.IO) {
        emit(repository.addMovieToList(listId, movieId))
    }


    class MovieListDetailsViewModelFactory(
        private val repository: MovieListDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListDetailsViewModel(repository) as T
        }
    }
}