package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.cadr.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity: AppCompatActivity() {

    var database : DatabaseReference ?= null
    var currentUser : FirebaseUser ?= null
    var storageReference : StorageReference ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        var bottomNavView = findViewById<BottomNavigationView>(R.id.profile_bottomNavView)
        bottomNavView.background = null
        bottomNavView.menu.getItem(2).isEnabled = false
        bottomNavView.menu.getItem(4).isChecked = true


        var profileUsername = findViewById<TextView>(R.id.profile_userName)
        var profileStatus = findViewById<TextView>(R.id.profile_userStatus)
        var profileImage = findViewById<CircleImageView>(R.id.profile_userImage)


        currentUser = FirebaseAuth.getInstance().currentUser
        var userId = currentUser!!.uid

        database = FirebaseDatabase.getInstance().reference.child("Users")
                .child(userId)

        database!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var username = snapshot!!.child("username").value
                var status = snapshot!!.child("status").value
                var image = snapshot!!.child("image").value
                var thumbnail = snapshot!!.child("thumbnail").value

                profileUsername.text = username.toString()
                profileStatus.text = status.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        // Edit the users username
        var editUsernameButton = findViewById<ImageButton>(R.id.editNameButton)
        editUsernameButton.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", profileUsername.text.toString().trim())
            intent.putExtra("status", profileStatus.text.toString().trim())
            startActivity(intent)
        }

        // Edit the users status
        var editStatusButton = findViewById<ImageButton>(R.id.editStatusButton)
        editStatusButton.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", profileUsername.text.toString().trim())
            intent.putExtra("status", profileStatus.text.toString().trim())
            startActivity(intent)
        }


    }

    fun profileFabBtnClick(view: View) {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    // for logout to go to sign in activity
    fun logOutClick(view: View){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, AccountActivity::class.java))
        finish()
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



//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        super.onOptionsItemSelected(item)
//
//        if (item.itemId == R.id.events) {
//            // intent to events activity
//            startActivity(Intent(this, EventsActivity::class.java))
//        } else if(item.itemId == R.id.chat) {
//            // intent to chat activity
//            startActivity(Intent(this, ChatActivity::class.java))
//        } else if(item.itemId == R.id.quiz) {
//            // intent to quiz activity
//            var intent = Intent(this, QuizActivity::class.java)
//            startActivity(intent)
//
//        }else if(item.itemId == R.id.account) {
//            // intent to profile activity
//            startActivity(Intent(this, ProfileActivity::class.java))
//        }
//
//        return true
//    }




}