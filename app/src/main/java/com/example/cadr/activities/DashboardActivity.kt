package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottom_app_nav, menu)

        return true
    }

    fun eventsOnClick(view: MenuItem){
        val intent = Intent(this, EventsActivity::class.java)
        startActivity(intent)
    }

    fun chatOnClick(view: MenuItem){
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }


    fun quizOnClick(view: MenuItem){
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }


    fun accountOnClick(view: MenuItem){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.events) {
            // intent to events activity
            startActivity(Intent(this, EventsActivity::class.java))
        }

        if(item.itemId == R.id.chat) {
            // intent to chat activity
            startActivity(Intent(this, ChatActivity::class.java))
        }

        if(item.itemId == R.id.quiz) {
            // intent to quiz activity
            var intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)

        }

        if(item.itemId == R.id.account) {
            // intent to profile activity
            startActivity(Intent(this, AccountActivity::class.java))
        }

        return true
    }

}