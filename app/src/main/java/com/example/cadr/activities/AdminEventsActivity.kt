package com.example.cadr.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cadr.R
import com.example.cadr.adapters.EventAdapter
import com.example.cadr.models.Event
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView

class EventsActivity: AppCompatActivity() {

    private lateinit var databaseRef : DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventList: ArrayList<Event>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_activity)

        recyclerView = findViewById(R.id.event_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        eventList = arrayListOf<Event>()
        showEvent()

    }

    private fun showEvent(){

        databaseRef = FirebaseDatabase.getInstance().getReference("Events")

        databaseRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                 if (snapshot.exists()){

                     for (eventSnapshot in snapshot.children){

                         val eventId = eventSnapshot.getValue(Event::class.java)
                         eventList.add(eventId!!)
                     }
                     recyclerView.adapter = EventAdapter(eventList )
                 }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }


    fun fabBtnClick(view: View) {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottom_app_nav, menu)

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


}