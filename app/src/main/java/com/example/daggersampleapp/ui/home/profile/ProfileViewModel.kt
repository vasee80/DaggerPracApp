package com.example.daggersampleapp.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggersampleapp.SessionManager
import com.example.daggersampleapp.model.User
import com.example.daggersampleapp.util.Resource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) :
    ViewModel() {

    fun getAuthenticatedUser(): LiveData<Resource<User>> {
        return sessionManager.getAuthUser()
    }
}