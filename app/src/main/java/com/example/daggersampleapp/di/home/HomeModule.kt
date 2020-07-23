package com.example.daggersampleapp.di.home

import com.example.daggersampleapp.network.home.HomeApi
import com.example.daggersampleapp.ui.home.post.PostListAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeModule {

    companion object {

        @Provides
        fun provideAdapter(): PostListAdapter {
            return PostListAdapter()
        }

        @Provides
        fun provideHomeApi(retrofit: Retrofit): HomeApi {
            return retrofit.create(HomeApi::class.java)
        }
    }
}