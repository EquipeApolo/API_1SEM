package com.cgmdigitalhouse.cinelist.moviedetails.photos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.cgmdigitalhouse.cinelist.R
import com.squareup.picasso.Picasso

class PhotoFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_full)

        val imgClose = findViewById<ImageView>(R.id.img_photoFullClose)
        val imgPhotoFull = findViewById<ImageView>(R.id.img_photoFull)

        val _photoFull = intent.getStringExtra("IMAGE")

        Picasso.get().load(_photoFull).into(imgPhotoFull);



        imgClose.setOnClickListener {
            finish()
        }

    }
}