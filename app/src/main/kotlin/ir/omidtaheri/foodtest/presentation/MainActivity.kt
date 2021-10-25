package ir.omidtaheri.foodtest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ir.omidtaheri.foodtest.R
import ir.omidtaheri.foodtest.base.BaseActivity
import ir.omidtaheri.foodtest.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.topAppBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.categoryListFragment,
                    R.id.searchFragment,
                    R.id.blankFragment,
                    R.id.blankFragment2
                ), binding.drawerLayout
            )

        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)
        binding.bottomNavigation.setupWithNavController(navController)

        setToolbarTitleObserver()
    }

    private fun setToolbarTitleObserver() {
        viewModel.toolbarTitle.observe(this, Observer { binding.topAppBar.title = it })
    }

    override fun inflateViewBinding(inflater: LayoutInflater): View? {
        binding = ActivityMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}