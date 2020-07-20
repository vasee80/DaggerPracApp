package com.example.daggersampleapp.di

import com.example.daggersampleapp.di.auth.AuthModule
import com.example.daggersampleapp.di.auth.AuthViewModelModule
import com.example.daggersampleapp.ui.auth.AuthActivity
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
}