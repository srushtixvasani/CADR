package com.example.cadr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // write into database
        var firebaseDb = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDb.getReference("messages")
        databaseReference.setValue("Hello World!")

        //read into database
        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                var value = snapshot!!.value

                Log.d("VALUE", value.toString())


            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("ERROR", error!!.message)
            }
        })


    }




    fun loginBtnClick(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun signUpBtnClick(view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}
