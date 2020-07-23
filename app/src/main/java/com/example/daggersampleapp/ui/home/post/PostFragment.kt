package com.example.daggersampleapp.ui.home.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggersampleapp.R
import com.example.daggersampleapp.util.Resource
import com.example.daggersampleapp.viewmodel.AppViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    private val TAG = "PostFragment"

    lateinit var viewModel: PostViewModel
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var adapter: PostListAdapter

    @Inject
    lateinit var providerFactory: AppViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)

        viewModel = ViewModelProvider(this, providerFactory).get(PostViewModel::class.java)

        initRecylerView()
        subscribeObserver()
        return view
    }

    private fun subscribeObserver() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer {
            //Log.d(TAG, "subscribeObserver: ${it.data}")
            when (it) {
                is Resource.Success -> it.data?.let { it1 -> adapter.setPosts(it1) }
                is Resource.Error -> Log.d(TAG, "subscribeObserver: ${it.message}")
            }
        })
    }

    private fun initRecylerView() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }
}