package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cadr.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity: AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        mAuth = FirebaseAuth.getInstance()

        var signUpButton = findViewById<ImageButton>(R.id.createAccBtn)
        signUpButton.setOnClickListener {
            var username = findViewById<EditText>(R.id.usernameEt).text.toString().trim()
            var email = findViewById<EditText>(R.id.passwordET).text.toString().trim()
            var password = findViewById<EditText>(R.id.passwordEt).text.toString().trim()

            if (username == "Admin" && email == "srushtivasani@gmail.com" && password == "admin123") {
               adminSignUp(username, email, password)
            } else if (!TextUtils.isEmpty(username) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                signUp(username, email, password)
            }else {
                Toast.makeText(this, "Please fill out all the fields",
                    Toast.LENGTH_LONG).show()
            }
        }

    }


    // Login button
    fun loginButtonClick(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    // Sign Up button
    fun signUpButtonClick(view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun signUp(username: String, email: String, password: String ){
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->

            if (task.isSuccessful){
                var currentUser = mAuth!!.currentUser
                var userId = currentUser!!.uid

                database = FirebaseDatabase.getInstance().reference.child("Users")
                    .child(userId)

                var userObj = HashMap<String, String>()
                userObj.put("username", username)
                // add email?
                userObj.put("status", "Hello World")
                userObj.put("image", "default")

                database!!.setValue(userObj).addOnCompleteListener {
                    task: Task<Void> ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_LONG)
                            .show()
                        var intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Sign Up Unsuccessful", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }else {

            }
        }
    }

    fun adminSignUp(username: String, email: String, password: String ){
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->

            if (task.isSuccessful){
                var currentUser = mAuth!!.currentUser
                var userId = currentUser!!.uid

                database = FirebaseDatabase.getInstance().reference.child("Admin")
                        .child(userId)

                var userObj = HashMap<String, String>()
                userObj.put("username", username)
                // add email?
                userObj.put("status", "Hello World")
                userObj.put("image", "default")

                database!!.setValue(userObj).addOnCompleteListener {
                    task: Task<Void> ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Admin sign Up Successful!", Toast.LENGTH_LONG)
                                .show()
                        var intent = Intent(this, AdminActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Admin sign Up Unsuccessful", Toast.LENGTH_LONG)
                                .show()
                    }
                }
            }else {

            }
        }
    }
}