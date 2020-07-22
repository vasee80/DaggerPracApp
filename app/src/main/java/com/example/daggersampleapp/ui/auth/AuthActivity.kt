package com.example.daggersampleapp.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.daggersampleapp.R
import com.example.daggersampleapp.model.User
import com.example.daggersampleapp.ui.home.HomeActivity
import com.example.daggersampleapp.util.Resource
import com.example.daggersampleapp.viewmodel.AppViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private val TAG = "AuthActivity"

    private lateinit var progressBar: ProgressBar

    lateinit var userIdTxt: EditText

    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: AppViewModelProviderFactory

    @Inject
    @JvmField
    var logo: Drawable? = null

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userIdTxt = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()

        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.observeAuthState().observe(this, Observer {
            when (it) {
                is Resource.Success -> successResult(it)
                is Resource.Loading -> showProgressbar(true)
                is Resource.Error -> errorResult(it)
            }
        })
    }

    private fun setLogo() {
        requestManager.load(logo)
            .into(findViewById(R.id.login_logo))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_button -> attemptLogin()
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(userIdTxt.text.toString())) {
            return
        }
        viewModel.authenticatedWithId(userIdTxt.text.toString().toInt())
    }

    private fun successResult(result: Resource<User>) {
        showProgressbar(false)
        Log.d(TAG, "successResult: ${result.data?.email}")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun errorResult(result: Resource<User>) {
        showProgressbar(false)
        Log.d(TAG, "errorResult: ${result.message}")
    }

    private fun showProgressbar(isVisible: Boolean) {
        if (isVisible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}