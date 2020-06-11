package com.org.vcart.utils

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("price")
    var price: String? = null  //,, override val size: Int

)