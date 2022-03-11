package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cadr.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        // open account activity after splash screen
        Handler().postDelayed({
            // open the account activity after the timer has passed
            startActivity(Intent(this, AccountActivity::class.java))
            // shut the activity
            finish()
        }, SPLASH_TIME
        )

    }


}
