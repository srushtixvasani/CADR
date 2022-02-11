package com.example.cadr

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DashboardActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        if (intent.extras != null){
            var username = intent.extras!!.get("username")

            Toast.makeText(this, username.toString(), Toast.LENGTH_LONG)
                .show()
        }


    }
}