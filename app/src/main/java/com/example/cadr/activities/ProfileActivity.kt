package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cadr.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)


    }

    // sign up button
    fun logOutClick(view: View){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, AccountActivity::class.java))
        finish()
    }



    // for logout
    // FirebaseAuth.getInstance().signOut()
    //startActivity(Intent(this, AccountActivity::class.java))
    // finish()
}