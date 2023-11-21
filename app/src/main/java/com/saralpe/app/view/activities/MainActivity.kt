package com.saralpe.app.view.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.saralpe.app.R
import com.saralpe.app.databinding.ActivityMainBinding
import com.saralpe.app.view.fragments.AccountFragment
import com.saralpe.app.view.fragments.HomeFragment
import com.saralpe.app.view.fragments.NotificationsFragment
import com.saralpe.app.view.fragments.StatisticsFragment
import java.util.ArrayList


class MainActivity : BaseActivity(),HomeFragment.OnDrawerListener,NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private var currentFragment: Fragment? = null

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener
        )
        loadFragment(HomeFragment(), "home", false)

        binding.fab.setOnClickListener {
            Permissions.check(
                this,
                Manifest.permission.CAMERA,
                null,
                object : PermissionHandler() {
                    override fun onGranted() {
                        // do your task.
                        resultLauncher.launch(Intent(context, QRCodeScannerActivity::class.java))
                    }

                    override fun onDenied(
                        context: Context?,
                        deniedPermissions: ArrayList<String>?
                    ) {
                        showAlert(
                            this@MainActivity,
                            "Camera permission is required for QR code scanning."
                        )
                    }
                })

        }

        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_nav -> {
                    loadFragment(HomeFragment(), "home", true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.statistics_nav -> {
                    loadFragment(StatisticsFragment(), "statistics", true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.notification_nav -> {
                    loadFragment(NotificationsFragment(), "notifications", true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account_nav -> {
                    loadFragment(AccountFragment(), "account", true)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun loadFragment(fragment: Fragment, tag: String, flag: Boolean) {
        currentFragment = fragment
        if (flag) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, tag)
                .commit()
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val contents =
                    data!!.getStringExtra("SCAN_RESULT") // This will contain the scanned QR code content
                // Handle the QR code content as needed
                showAlert(context, contents.toString())
            }
        }

    private fun toggleDrawer(){
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }
        else{
            binding.drawer.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {

        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            toggleDrawer()
        }
        else if (currentFragment is HomeFragment) {
            onBackPressedDispatcher.onBackPressed()
        } else {
            loadFragment(HomeFragment(), "home", true)
        }
    }

    override fun changeDrawer() {
        toggleDrawer()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        toggleDrawer()
        return true
    }
}