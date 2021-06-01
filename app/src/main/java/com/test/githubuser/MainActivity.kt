package com.test.githubuser

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.test.githubuser.databinding.ActivityMainBinding
import com.test.githubuser.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
    private val navController = navHostFragment?.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (navController != null) {
            NavigationUI.setupActionBarWithNavController(this, navController)
        }
    }

    override fun onNavigateUp(): Boolean =
        navController?.navigateUp() == true || super.onSupportNavigateUp()
}