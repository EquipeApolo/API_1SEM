package com.cgmdigitalhouse.cinelist.moviedetails.reviews.model

import com.google.gson.annotations.SerializedName

data class ReviewModel (
        @SerializedName("author_details") val authorDetails: AuthorDetailsModel,
        @SerializedName("content") val content: String
)