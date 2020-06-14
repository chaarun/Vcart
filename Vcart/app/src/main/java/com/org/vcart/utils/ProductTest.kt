package com.org.vcart.utils

import com.google.gson.annotations.SerializedName

data class ProductTest(
    @SerializedName("products")
    var products: List<Product> = arrayListOf()


)