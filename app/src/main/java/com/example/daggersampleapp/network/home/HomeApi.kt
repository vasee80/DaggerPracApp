package com.example.daggersampleapp.network.home

import com.example.daggersampleapp.model.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("posts")
    fun getPostFromUser(
        @Query("userId") id: Int
    ): Flowable<List<Post>>
}