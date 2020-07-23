package com.example.daggersampleapp.di.home

import com.example.daggersampleapp.ui.home.post.PostFragment
import com.example.daggersampleapp.ui.home.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}