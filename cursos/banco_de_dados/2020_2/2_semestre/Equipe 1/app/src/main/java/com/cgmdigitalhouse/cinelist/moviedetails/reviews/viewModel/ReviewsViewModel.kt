package com.cgmdigitalhouse.cinelist.moviedetails.reviews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.model.ReviewModel
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.repository.ReviewsRepository
import kotlinx.coroutines.Dispatchers

class ReviewsViewModel(
    private val repository: ReviewsRepository
): ViewModel()  {
    private var _reviews: List<ReviewModel>? = null

    fun getReviews(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getReviews(id)

        _reviews = response.results
        emit(response.results)
    }

    class ReviewsViewModelFactory(
        private val repository: ReviewsRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ReviewsViewModel(repository) as T
        }
    }
}