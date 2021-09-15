package com.cgmdigitalhouse.cinelist.search.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.search.repository.SearchRepository
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import kotlinx.coroutines.Dispatchers

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    var movies: List<MovieModel> = listOf()
    private var moviesBeforeSearch = listOf<MovieModel>()
    private var _page: Int = 1
    private var _totalPages: Int = 1

    fun getMovies(query: String = "", page: Int = 1) = liveData(Dispatchers.IO) {
        _page = 1

        if (query.isEmpty()) {
            emit(listOf<MovieModel>())
        } else {
            val response = repository.searchMovies(query, page)
            movies = response.results
            _totalPages = response.total_pages

            emit(response.results)
        }
    }

    fun search(query: String? = null, page: Int = 1) = liveData(Dispatchers.IO) {
        moviesBeforeSearch = movies
        _page = 1

        val response = repository.searchMovies(query.toString(), page)
        _totalPages = response.total_pages
        emit(response.results)
    }

    fun proximaPagina(name: String) = liveData(Dispatchers.IO) {

        if(_page <= _totalPages) {
            _page++

            val response = repository.searchMovies(name, _page)
            emit(response.results)
        }
    }

    fun oldMovieList() = moviesBeforeSearch

    class SearchViewModelFactory(
        private val repository: SearchRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }

    }
}