package com.cgmdigitalhouse.cinelist.data.api

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

//        val languageRequest = "pt-BR"
        val publicKey = "e26efe841c7c4f072e9ca3bc052b8907"
        val httpUrl = request.url().newBuilder()
                .addQueryParameter(API_KEY, publicKey)
                .build()

        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY = "api_key"
        private const val API_LANG = "language"
    }
}