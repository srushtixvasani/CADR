package com.example.cadr.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cadr.R
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import id.zelory.compressor.Compressor
import java.io.ByteArrayOutputStream

import java.io.File

class ProfileActivity : AppCompatActivity() {

    var database: DatabaseReference? = null
    var currentUser: FirebaseUser? = null
    var storageReference: StorageReference? = null

    var GALLERY: Int = 1

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

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://cadr-9ddba.appspot.com")

        database = FirebaseDatabase.getInstance().reference.child("Users")
            .child(userId)

        database!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var username = snapshot!!.child("username").value
                var status = snapshot!!.child("status").value
                var image = snapshot!!.child("image").value.toString()
                var thumbnail = snapshot!!.child("thumbnail").value

                profileUsername.text = username.toString()
                profileStatus.text = status.toString()

                if(image != "default"){
                    Picasso.get().load(image).placeholder(R.drawable.ic_user)
                        .into(profileImage)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        // Edit the users username
        var editUsernameButton = findViewById<ImageButton>(R.id.editNameButton)
        editUsernameButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", profileUsername.text.toString().trim())
            intent.putExtra("status", profileStatus.text.toString().trim())
            startActivity(intent)
        }

        // Edit the users status
        var editStatusButton = findViewById<ImageButton>(R.id.editStatusButton)
        editStatusButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", profileUsername.text.toString().trim())
            intent.putExtra("status", profileStatus.text.toString().trim())
            startActivity(intent)
        }

        // Edit the users image
        var editImageButton = findViewById<ImageButton>(R.id.editPictureButton)
        editImageButton.setOnClickListener {
            var galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(galleryIntent, "Please choose your profile picture."),
                GALLERY
            )
        }


    }

    // following the documentation on https://github.com/ArthurHub/Android-Image-Cropper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == GALLERY && resultCode == Activity.RESULT_OK) {
            var picture: Uri? = data!!.data
            CropImage.activity(picture).setAspectRatio(1, 1).start(this)
        }

        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)

            if (resultCode === Activity.RESULT_OK) {
                val resultUri = result.uri
                var userId = currentUser!!.uid
                var thumbnailFile = File(resultUri.path)
                var thumbnailBitmap = Compressor(this).setMaxHeight(200)
                    .setMaxWidth(200)
                    .setQuality(60)
                    .compressToBitmap(thumbnailFile)

                // Uploading images to firebase
                var byteArrayOutput = ByteArrayOutputStream()
                thumbnailBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutput)

                var thumbnailByteArray: ByteArray
                thumbnailByteArray = byteArrayOutput.toByteArray()

                var filePath = storageReference!!.child("forum_profile_pics")
                    .child(userId + ".png")

                // This is another directory for compressed images used for the forum screen
                var thumbnailFPath = storageReference!!.child("forum_profile_pics")
                    .child("thumbnails")
                    .child(userId + ".png")

                filePath.putFile(resultUri).addOnCompleteListener {
                    taskSnapshot: Task<UploadTask.TaskSnapshot> ->
                        if (taskSnapshot.isSuccessful) {
                            var imageUrl = resultUri.toString()
                                //filePath.downloadUrl.toString()   //This is where the magic starts :)

                            //upload image
                            var uploadTask: UploadTask = thumbnailFPath.putBytes(thumbnailByteArray)

                            uploadTask.addOnCompleteListener {
                                task: Task<UploadTask.TaskSnapshot> ->
                                    var thumbnailUrl = resultUri.toString()

                                        //filePath.downloadUrl.toString()   //and here as well!

                                    if (task.isSuccessful) {
                                        var updateObj = HashMap<String, Any>()
                                        updateObj.put("image", imageUrl)
                                        updateObj.put("thumbnail", thumbnailUrl)

                                        //save profile image
                                        database!!.updateChildren(updateObj).addOnCompleteListener {
                                            task: Task<Void> ->
                                                if (task.isSuccessful) {
                                                    Toast.makeText(
                                                        this,
                                                        "Profile image saved",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun profileFabBtnClick(view: View) {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    // for logout to go to sign in activity
    fun logOutClick(view: View) {
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

        if (item.itemId == R.id.chat) {
            // intent to chat activity
            startActivity(Intent(this, ChatActivity::class.java))
        }

        if (item.itemId == R.id.quiz) {
            // intent to quiz activity
            var intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)

        }

        if (item.itemId == R.id.account) {
            // intent to profile activity
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        return true
    }

    fun eventsOnClick(view: MenuItem) {
        val intent = Intent(this, EventsActivity::class.java)
        startActivity(intent)
    }

    fun chatOnClick(view: MenuItem) {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }


    fun quizOnClick(view: MenuItem) {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }


    fun profileOnClick(view: MenuItem) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }




}