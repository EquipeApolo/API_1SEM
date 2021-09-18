package com.cgmdigitalhouse.cinelist.moviedetails.cast.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.moviedetails.cast.model.CastModel
import com.cgmdigitalhouse.cinelist.moviedetails.cast.repository.CastRepository
import kotlinx.coroutines.Dispatchers

class CastViewModel(
    private val repository: CastRepository
): ViewModel()  {
    private var _cast: List<CastModel>? = null

    fun getCast(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getCast(id)

        _cast = response.cast
        emit(_cast)
    }

    class CastViewModelFactory(
        private val repository: CastRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CastViewModel(repository) as T
        }
    }
}