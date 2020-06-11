package com.org.vcart.service

import com.org.vcart.utils.Product
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("data")
    fun getData(): Call<List<Product>>
}
