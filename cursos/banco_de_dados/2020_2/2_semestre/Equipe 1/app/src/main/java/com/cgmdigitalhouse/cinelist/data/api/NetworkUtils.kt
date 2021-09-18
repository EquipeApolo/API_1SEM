package com.cgmdigitalhouse.cinelist.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w342"

        fun getRetrofitInstance(): Retrofit {
            val client = OkHttpClient
                    .Builder()
                    .addInterceptor(NetworkInterceptor())
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }
    }
}