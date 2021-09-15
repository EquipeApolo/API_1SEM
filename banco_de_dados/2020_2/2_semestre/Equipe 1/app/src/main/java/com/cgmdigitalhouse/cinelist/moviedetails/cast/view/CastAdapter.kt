package com.cgmdigitalhouse.cinelist.moviedetails.cast.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.moviedetails.cast.model.CastModel
import com.squareup.picasso.Picasso

class CastAdapter(private val dataset: List<CastModel>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val imageVw: ImageView = view.findViewById(R.id.imgCast)
        private val ActorName: TextView = view.findViewById(R.id.txtCastActor)
        private val CharacterName: TextView = view.findViewById(R.id.txtCastCharacter)

        fun bind(cast: CastModel) {
            ActorName.text = cast.name
            CharacterName.text = cast.character

            if (cast.profilePath != null) {

                Log.d("IMAGE_CAST_TEST", cast.getPathImage())

                val param = imageVw.layoutParams as ViewGroup.MarginLayoutParams
                param.setMargins(0, 0, 0, 0)
                imageVw.layoutParams = param


                Glide.with(view.context)
                    .load(cast.getPathImage())
                    .transform(CenterCrop(), RoundedCorners(8))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageVw)

            } else {
                val param = imageVw.layoutParams as ViewGroup.MarginLayoutParams
                param.setMargins(90, 100, 90, 100)
                imageVw.layoutParams = param

                Glide.with(view.context)
                    .load(R.drawable.ic_round_person_24)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageVw)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)

        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}