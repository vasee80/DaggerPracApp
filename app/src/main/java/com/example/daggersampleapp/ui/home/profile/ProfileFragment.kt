package com.example.daggersampleapp.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggersampleapp.R
import com.example.daggersampleapp.model.User
import com.example.daggersampleapp.util.Resource
import com.example.daggersampleapp.viewmodel.AppViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    lateinit var viewModel: ProfileViewModel

    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var website: TextView

    @Inject
    lateinit var providerFactory: AppViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name = view.findViewById(R.id.nameTxtView)
        email = view.findViewById(R.id.emailTxtView)
        website = view.findViewById(R.id.websiteTxtView)

        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> setUserDetails(it.data)
                is Resource.Error -> setErrors(it.message)
            }
        })
    }

    private fun setErrors(string: String?) {
        name.text = string
        email.text = getString(R.string.error_text)
        website.text = getString(R.string.error_text)
    }

    private fun setUserDetails(user: User?) {
        name.text = user?.userName
        email.text = user?.email
        website.text = user?.website
    }
}