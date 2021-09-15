package com.cgmdigitalhouse.cinelist.moviedetails.photos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.moviedetails.photos.model.PosterModel
import java.lang.Exception

class PhotosAdapter (private val dataset: List<PosterModel>, private val listener: (PosterModel) -> Unit): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageVw: ImageView = view.findViewById(R.id.imgPhoto)

        fun bind(foto: PosterModel) {

            try {
                Glide.with(itemView.context)
                    .load(foto.getPathPoster())
                    .transform()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageVw)
            } catch (ex: Exception) {

            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)

        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)

        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = dataset.size
}