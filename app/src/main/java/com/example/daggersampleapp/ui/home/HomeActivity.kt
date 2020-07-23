package com.example.daggersampleapp.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.daggersampleapp.BaseActivity
import com.example.daggersampleapp.R
import com.example.daggersampleapp.ui.home.post.PostFragment

class HomeActivity : BaseActivity() {

    private val TAG = "HomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadProfileFragment()
    }

    private fun loadProfileFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PostFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> sessionManager.logout()
        }
        return super.onOptionsItemSelected(item)
    }
}