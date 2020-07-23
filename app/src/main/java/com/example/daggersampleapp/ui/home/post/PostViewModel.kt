package com.example.daggersampleapp.ui.home.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggersampleapp.SessionManager
import com.example.daggersampleapp.model.Post
import com.example.daggersampleapp.network.home.HomeApi
import com.example.daggersampleapp.util.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(val sessionManager: SessionManager, val homeApi: HomeApi) :
    ViewModel() {

    private val TAG = "PostViewModel"

    private lateinit var posts: MediatorLiveData<Resource<List<Post>>>

    fun observePosts(): LiveData<Resource<List<Post>>> {
        posts = MediatorLiveData()
        posts.value = Resource.Loading(null)

        val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
            homeApi.getPostFromUser(sessionManager.getAuthUser().value!!.data!!.id)
                .onErrorReturn(Function<Throwable, List<Post>> {
                    return@Function listOf(Post(-1, -1, "", ""))
                })
                .map(Function<List<Post>, Resource<List<Post>>> {
                    if (it.isNotEmpty() && it[0].id == -1) {
                        return@Function Resource.Error("Something went wrong", null)
                    }
                    return@Function Resource.Success(it)
                })
                .subscribeOn(Schedulers.io())
        )

        posts.addSource(source) {
            posts.value = it
            posts.removeSource(source)
        }

        return posts
    }
}