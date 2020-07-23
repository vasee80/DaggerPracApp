package com.example.daggersampleapp.di.home

import androidx.lifecycle.ViewModel
import com.example.daggersampleapp.di.ViewModelKey
import com.example.daggersampleapp.ui.home.post.PostViewModel
import com.example.daggersampleapp.ui.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}