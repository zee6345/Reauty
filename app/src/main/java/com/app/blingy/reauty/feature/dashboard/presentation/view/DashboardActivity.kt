package com.app.blingy.reauty.feature.dashboard.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.blingy.reauty.R
import com.app.blingy.reauty.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // to show the app theme
        setTheme(R.style.Reauty_Light)
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavRemoveWhiteSpace()
        setupNavController()
    }

    /**
     * walk around to remove the white space at the start of the BottomNavigationView
     */
    private fun bottomNavRemoveWhiteSpace() {
        binding.bottomNavigation.apply {
            background = null
            menu.getItem(2).isEnabled = false
        }
    }

    private fun setupNavController() {
        Timber.d("setupNavController: called")
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupBottomNavigation(navController)
    }

    private fun setupBottomNavigation(navController: NavController) {
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

}