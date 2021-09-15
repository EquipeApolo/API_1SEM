package com.cgmdigitalhouse.cinelist.moviedetails.photos.repository

class PhotosRepository{
    private val client = IPhotosEndpoint.endpoint

    suspend fun getPhotos(id: Int) = client.getPhotos(id)
}