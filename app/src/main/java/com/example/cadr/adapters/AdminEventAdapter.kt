package com.example.cadr.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cadr.R
import com.example.cadr.models.Event

class EventAdapter(private var eventList : ArrayList<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.events_item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newEventList = eventList[position]
        holder.eventName.text = newEventList.eventName
        holder.eventDate.text = newEventList.eventDate
        holder.eventLink.text = newEventList.eventLink
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.eventName)
        val eventDate: TextView = itemView.findViewById(R.id.eventDate)
        val eventLink: TextView = itemView.findViewById(R.id.eventLink)

    }


}