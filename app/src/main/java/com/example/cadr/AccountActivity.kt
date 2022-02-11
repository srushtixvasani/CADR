package com.example.cadr

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AccountActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.account_activity)
        var loginButton = findViewById<MaterialButton>(R.id.loginButton)
        var signUpButton = findViewById<MaterialButton>(R.id.signUpButton)

    }

    override fun onResume() {
        super.onResume()
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    // login button
    fun loginClick(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    // sign up button
    fun signUpClick(view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }



}