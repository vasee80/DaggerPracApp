package com.example.daggersampleapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("email") val email: String,
    @SerializedName("website") val website: String
)