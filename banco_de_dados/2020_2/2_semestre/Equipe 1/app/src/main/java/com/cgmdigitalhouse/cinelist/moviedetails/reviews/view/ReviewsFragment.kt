package com.cgmdigitalhouse.cinelist.moviedetails.reviews.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.model.ReviewModel
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.repository.ReviewsRepository
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.viewModel.ReviewsViewModel

private const val ARG_PARAM1 = "ID"

class ReviewsFragment : Fragment() {
    lateinit var _view: View
    private lateinit var _viewModel: ReviewsViewModel
    private var param1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _view = inflater.inflate(R.layout.fragment_reviews, container, false)

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(
                this,
                ReviewsViewModel.ReviewsViewModelFactory(ReviewsRepository())
        ).get(ReviewsViewModel::class.java)

        _viewModel.getReviews(param1).observe(viewLifecycleOwner, {
            createReviewList(it)
        })

    }

    fun createReviewList(reviews: List<ReviewModel>) {
        val viewManagerReviews = LinearLayoutManager(activity)
        val recyclerViewReviews = view?.findViewById<RecyclerView>(R.id.rcyVwReviews)

        val viewAdapterReviews = ReviewsAdapter(reviews)

        recyclerViewReviews?.apply {
            layoutManager = viewManagerReviews
            adapter = viewAdapterReviews
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ReviewsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

}