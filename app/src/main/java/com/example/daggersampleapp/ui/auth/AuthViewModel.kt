package com.example.daggersampleapp.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.daggersampleapp.SessionManager
import com.example.daggersampleapp.model.User
import com.example.daggersampleapp.network.auth.AuthApi
import com.example.daggersampleapp.util.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val TAG = "AuthViewModel"

    fun authenticatedWithId(userId: Int) {
        Log.d(TAG, "authenticatedWithId: authentication attempt ...")
        sessionManager.authenticatedWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<Resource<User>> =
        LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn(Function<Throwable, User> { _: Throwable ->
                    return@Function User(-1, "", "", "")
                })
                .map(Function<User, Resource<User>> {
                    if (it.id == -1) {
                        return@Function Resource.Error("Could not authenticate", null)
                    }
                    return@Function Resource.Success(it)
                })
                .subscribeOn(Schedulers.io())
        )

    fun observeAuthState(): LiveData<Resource<User>> {
        return sessionManager.getAuthUser()
    }
}