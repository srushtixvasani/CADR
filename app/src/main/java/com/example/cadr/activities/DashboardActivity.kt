package com.example.cadr.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.cadr.R
import com.example.cadr.adapters.DashboardPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout


class DashboardActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var dashboardAdapter: DashboardPagerAdapter? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        //supportActionBar!!.title = "Dashboard"

        dashboardAdapter = DashboardPagerAdapter(supportFragmentManager)

        var dashboardViewPager = findViewById<ViewPager>(R.id.dashboard_viewpager)
        dashboardViewPager.adapter = dashboardAdapter

        var tabs = findViewById<TabLayout>(R.id.dashboard_tab)
        tabs.setupWithViewPager(dashboardViewPager)

        var bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavView.background = null
        bottomNavView.menu.getItem(2).isEnabled = false



        if (intent.extras != null){
            var username = intent.extras!!.get("username")

            Toast.makeText(this, username.toString(), Toast.LENGTH_LONG)
                .show()
        }


    }
}