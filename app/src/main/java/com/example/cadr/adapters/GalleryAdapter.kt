package com.example.cadr.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.cadr.R


class GalleryRecyclerViewAdapter : BaseAdapter() {

    private val context: Context? = null


    var imageArray = intArrayOf(
        R.raw.image1,
        R.raw.image2,
        R.raw.image3,
        R.raw.image4,
        R.raw.image5,
        R.raw.image6,
        R.raw.image7,
        R.raw.image8,
        R.raw.image9,
        R.raw.image10,
        R.raw.image11,
        R.raw.image12,
        R.raw.image13,
        R.raw.image14
    )


    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}



