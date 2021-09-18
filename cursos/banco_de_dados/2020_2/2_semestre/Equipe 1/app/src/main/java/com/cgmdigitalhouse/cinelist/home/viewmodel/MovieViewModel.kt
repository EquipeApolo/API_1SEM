package com.cgmdigitalhouse.cinelist.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.home.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repository: MovieRepository): ViewModel() {
    private var _movies: List<MovieModel> = listOf()

    fun getMoviesPopular() = liveData(Dispatchers.IO) {
        val response = repository.getMoviesPopular()

        _movies = response.results
        emit(response.results)
    }

    fun getMoviesNowPaying() = liveData(Dispatchers.IO) {
        val response = repository.getMoviesNowPaying()

        _movies = response.results
        emit(response.results)
    }

    class MovieViewModelFactory(
        private val repository: MovieRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }
}