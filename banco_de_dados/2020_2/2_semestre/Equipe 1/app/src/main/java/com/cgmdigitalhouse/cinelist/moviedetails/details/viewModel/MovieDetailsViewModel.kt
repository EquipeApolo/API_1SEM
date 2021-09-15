package com.cgmdigitalhouse.cinelist.moviedetails.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.moviedetails.details.repository.MovieDetailsRepository
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import kotlinx.coroutines.Dispatchers

class MovieDetailsViewModel(
    val repository: MovieDetailsRepository
): ViewModel()  {
    private var _movieDetails: MovieModel? = null

    fun getMovieDetails(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getMovieDetails(id)

        _movieDetails = response
        emit(response)
    }

     fun addMovieToList(idMovie:Long, idListMovie:Long) = liveData(Dispatchers.IO){
        repository.inserirListMovieCrossRef(ListMovieCrossRefEntity(idListMovie,idMovie))
        emit(ListMovieCrossRefEntity(idListMovie,idMovie))
    }

    fun checkIfMovieIsOnList(idMovie: Long, idListMovie: Long)= liveData(Dispatchers.IO){
        emit(repository.checkIfMovieIsOnList(idMovie,idListMovie))
    }

    class MovieDetailsViewModelFactory(
        private val repository: MovieDetailsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(repository) as T
        }
    }
}