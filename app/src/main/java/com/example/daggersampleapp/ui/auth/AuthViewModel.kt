package com.example.daggersampleapp.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.daggersampleapp.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    private val TAG = "AuthViewModel"
    
    init {
        Log.d(TAG, "In init view model working... ")

        authApi.getUser(2)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d(TAG, "view : ${it.email}")
                },
                {
                    Log.d(TAG, "error : ${it.message}")
                })
    }
}