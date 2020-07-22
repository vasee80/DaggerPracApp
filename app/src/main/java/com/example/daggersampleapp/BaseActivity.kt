package com.example.daggersampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.daggersampleapp.ui.auth.AuthActivity
import com.example.daggersampleapp.util.Resource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer {
            when (it) {
                is Resource.Success -> Log.d(TAG, "subscribeObservers: login success")
                is Resource.Loading -> Log.d(TAG, "subscribeObservers: loading ...")
                is Resource.Error -> Log.d(TAG, "subscribeObservers: error in subscribe ...")
                is Resource.Logout -> navToLogin()
            }
        })
    }

    private fun navToLogin() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}