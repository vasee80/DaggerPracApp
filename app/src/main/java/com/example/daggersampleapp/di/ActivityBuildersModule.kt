package com.example.daggersampleapp.di

import com.example.daggersampleapp.di.auth.AuthModule
import com.example.daggersampleapp.di.auth.AuthViewModelModule
import com.example.daggersampleapp.di.home.HomeFragmentBuildersModule
import com.example.daggersampleapp.di.home.HomeModule
import com.example.daggersampleapp.di.home.HomeViewModelModule
import com.example.daggersampleapp.ui.auth.AuthActivity
import com.example.daggersampleapp.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            ViewModelFactoryModule::class,
            AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(
        modules = [
            HomeFragmentBuildersModule::class,
            HomeViewModelModule::class,
            HomeModule::class]
    )
    abstract fun contributeHomeActivity(): HomeActivity
}