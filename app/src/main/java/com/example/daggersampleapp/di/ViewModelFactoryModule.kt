package com.example.daggersampleapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.daggersampleapp.viewmodel.AppViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindAppViewModelFactory(appViewModelProviderFactory: AppViewModelProviderFactory): ViewModelProvider.Factory
}