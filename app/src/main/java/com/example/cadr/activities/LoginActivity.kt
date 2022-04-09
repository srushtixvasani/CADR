package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cadr.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null

    private val TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // write into database
        mAuth = FirebaseAuth.getInstance()

        var loginBtn = findViewById<ImageButton>(R.id.loginAccBtn)


        loginBtn.setOnClickListener {
            var email = findViewById<EditText>(R.id.emailET).text.toString().trim()
            var password = findViewById<EditText>(R.id.passwordET).text.toString().trim()

            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun loginUser(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                // for admin login
                if (email == "srushtivasani@gmail.com" && password == "admin123") {
                    if (task.isSuccessful) {
                        //splits the email into the username with index 0 as the username
                        var username = email.split("@")[0]

                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG)
                            .show()

                        var intent = Intent(this, AdminActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.d(TAG, "ADMIN LOGIN NOT WORKING")
                    }

                } else {
                    // for normal users login
                    if (task.isSuccessful) {
                        //splits the email into the username with index 0 as the username
                        var username = email.split("@")[0]

                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT)
                            .show()

                        var intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "Login Unsuccessful!", Toast.LENGTH_SHORT)
                            .show()
                        Log.d(TAG, "USER LOGIN NOT WORKING")
                    }
                }

            }
    }


    fun loginBtnClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun signUpBtnClick(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}
