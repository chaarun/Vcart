package com.org.vcart.service

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIConfigURL {

    //val BASE_URL = "https://all-spices.com/api/products/"
   // val API_URL = "https://www.mocky.io/v2/5def7b172f000063008e0aa2/"  //given

   // val API_URL ="https://run.mocky.io/v3/e7feea79-7aaf-4fe1-be9b-20abe0a55754/" //products = rate
   val API_URL = "https://run.mocky.io/v3/9d37b64a-84fc-45e4-845d-a295b2bdb888/" //no prioducts
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
