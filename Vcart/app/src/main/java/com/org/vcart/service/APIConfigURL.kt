package com.org.vcart.service

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIConfigURL {
    val API_URL = "https://www.mocky.io/v2/5def7b172f000063008e0aa2/"
    private var retrofit: Retrofit? = null
    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitokHttpClient(context: Context): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}
