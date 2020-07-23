package com.example.daggersampleapp.ui.home.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggersampleapp.databinding.LayoutPostListItemBinding
import com.example.daggersampleapp.model.Post


class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    var postList: List<Post> = ArrayList()

    class PostViewHolder(private val binding: LayoutPostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutPostListItemBinding.inflate(layoutInflater, parent, false)

                return PostViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holderPost: PostViewHolder, position: Int) {
        val item = postList[position]
        holderPost.bind(item)
        /*holder.itemView.setOnClickListener {
            viewModel.selectedMovie.value = item
        }*/
    }

    fun setPosts(posts: List<Post>) {
        postList = posts
        notifyDataSetChanged()
    }
}