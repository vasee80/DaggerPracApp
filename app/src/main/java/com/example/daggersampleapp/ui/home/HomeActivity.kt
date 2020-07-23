package com.example.daggersampleapp.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.daggersampleapp.BaseActivity
import com.example.daggersampleapp.R
import com.google.android.material.navigation.NavigationView

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "HomeActivity"

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        initNavigation()
    }

    private fun initNavigation() {
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> sessionManager.logout()
            android.R.id.home -> handleDrawer()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_profile -> navToProfile()
            R.id.nav_post -> if (isValidDestination(R.id.postFragment)) Navigation.findNavController(
                this,
                R.id.nav_host_fragment
            ).navigate(R.id.postFragment)
        }

        menuItem.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun navToProfile() {
        val navOptions: NavOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .build()

        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
            R.id.profileFragment,
            null,
            navOptions
        )
    }

    private fun isValidDestination(destinationId: Int): Boolean {
        return destinationId != Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawerLayout
        )
    }

    private fun handleDrawer(): Boolean {
        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            false
        }
    }
}