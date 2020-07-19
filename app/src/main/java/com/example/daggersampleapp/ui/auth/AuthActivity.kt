package com.example.daggersampleapp.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.daggersampleapp.R
import com.example.daggersampleapp.viewmodel.AppViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"

    lateinit var viewModel : AuthViewModel

    @Inject
    lateinit var providerFactory : AppViewModelProviderFactory

    @Inject
    @JvmField
    var logo : Drawable? = null

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
    }

    private fun setLogo(){
        requestManager.load(logo)
            .into(findViewById(R.id.login_logo))
    }
}