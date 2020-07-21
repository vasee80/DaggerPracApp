package com.example.daggersampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.daggersampleapp.model.User
import com.example.daggersampleapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val TAG = "SessionManager"

    private var cachedUser: MediatorLiveData<Resource<User>> = MediatorLiveData()

    fun authenticatedWithId(source: LiveData<Resource<User>>) {
        cachedUser.value = Resource.Loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun getAuthUser(): LiveData<Resource<User>> {
        return cachedUser
    }

    fun logout() {
        cachedUser.value = Resource.Loading(null)
    }
}