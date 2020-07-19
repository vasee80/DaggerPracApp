package com.example.daggersampleapp.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {
    private val TAG = "AuthViewModel"
    
    init {
        Log.d(TAG, "In init view model working... ")
    }
}