package com.example.livesound

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.livesound.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val headerView = navView.getHeaderView(0)
        val closeButton = headerView.findViewById<FloatingActionButton>(R.id.close_main_menu)
        val homeMenuItem = navView.menu.findItem(R.id.nav_home)
        val profileMenuItem = navView.menu.findItem(R.id.nav_profile)
        val playerMenuItem = navView.menu.findItem(R.id.nav_player)
        val playListMenuItem = navView.menu.findItem(R.id.nav_playlist)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_player, R.id.nav_playlist
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        closeButton.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        homeMenuItem.setOnMenuItemClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_home)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        profileMenuItem.setOnMenuItemClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_profile)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        playerMenuItem.setOnMenuItemClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_player)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        playListMenuItem.setOnMenuItemClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_playlist)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}