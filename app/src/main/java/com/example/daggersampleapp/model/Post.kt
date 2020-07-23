package com.example.daggersampleapp.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") var id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)