package com.cgmdigitalhouse.cinelist.moviedetails.reviews.repository

import com.cgmdigitalhouse.cinelist.data.api.NetworkUtils
import com.cgmdigitalhouse.cinelist.data.model.ResponseModel
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.model.ReviewModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IReviewsEndpoint {
    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(
        @Path("movieId") movieId: Int): ResponseModel<ReviewModel>

    companion object {
        val endpoint: IReviewsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IReviewsEndpoint::class.java)
        }
    }
}