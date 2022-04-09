package com.example.cadr.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.cadr.R
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditProfileActivity : AppCompatActivity() {
    var database : DatabaseReference?= null
    var currentUser : FirebaseUser?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)

        var bottomNavView = findViewById<BottomNavigationView>(R.id.editProfile_bottomNavView)
        bottomNavView.background = null
        bottomNavView.menu.getItem(2).isEnabled = false
        bottomNavView.menu.getItem(4).isChecked = true

        var usernameUpdate = findViewById<EditText>(R.id.edit_userName)
        var statusUpdate = findViewById<EditText>(R.id.edit_userStatus)

//        if (intent.extras != null && intent.extras!!.equals("username")){
//            var oldUserName = intent.extras!!.get("username")
//            usernameUpdate.setText(oldUserName.toString())
//        }

        if (intent.extras != null ){
            var oldUserName = intent.extras!!.get("username")
            usernameUpdate.setText(oldUserName.toString())
            var oldUserStatus = intent.extras!!.get("status")
            statusUpdate.setText(oldUserStatus.toString())
        }

        // if there are no values for either the username or status
        if(intent.extras!!.equals(null)){
            usernameUpdate.setText("Enter your new username")
            statusUpdate.setText("Enter your new status")
        }

        var updateNameButton = findViewById<ImageButton>(R.id.updateNameButton)
        var updateStatusButton = findViewById<ImageButton>(R.id.updateStatusButton)

        updateNameButton.setOnClickListener{

        }

        // updates users status
        updateStatusButton.setOnClickListener{
            currentUser = FirebaseAuth.getInstance().currentUser
            var userId = currentUser!!.uid
            database = FirebaseDatabase.getInstance().reference.child("Users").child(userId)

            var newStatus = statusUpdate.text.toString().trim()
            database!!.child("status").setValue(newStatus).addOnCompleteListener {
                task : Task<Void> ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Your status has been updated!", Toast.LENGTH_LONG)
                        .show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                } else {
                    Toast.makeText(this, "Your status has not been updated", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottom_app_nav, menu)

        return true
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
            startActivity(Intent(this, ProfileActivity::class.java))
        }

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


    fun profileOnClick(view: MenuItem){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }



    fun editProfileFabBtnClick(view: View) {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

}