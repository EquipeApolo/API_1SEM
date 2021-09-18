package com.cgmdigitalhouse.cinelist.moviedetails.reviews.view

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.moviedetails.reviews.model.ReviewModel

class ReviewsAdapter(private val dataset: List<ReviewModel>): RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    class ReviewsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val reviewTxt: TextView = view.findViewById(R.id.txtReview)
        private val rateTxt: TextView = view.findViewById(R.id.txtRateReviews)
        private val usernameTxt: TextView = view.findViewById(R.id.txtUsernameReviews)

        fun bind(review: ReviewModel) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                reviewTxt.text = Html.fromHtml(review.content , Html.FROM_HTML_MODE_COMPACT);
            } else {
                reviewTxt.text = Html.fromHtml(review.content );
            }


            rateTxt.text = review.authorDetails.rating.toDouble().toString()
            usernameTxt.text = review.authorDetails.username
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)

        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}