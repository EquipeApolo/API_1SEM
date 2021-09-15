package com.cgmdigitalhouse.cinelist.moviedetails.reviews.repository

class ReviewsRepository{
    private val client = IReviewsEndpoint.endpoint

    suspend fun getReviews(id: Int) = client.getReviews(id)
}