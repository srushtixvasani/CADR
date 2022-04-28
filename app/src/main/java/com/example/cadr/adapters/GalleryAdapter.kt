package com.example.cadr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cadr.R
import com.example.cadr.models.Gallery


class GalleryAdapter( var context : Context) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    var imageList = emptyList<Gallery>()

    internal fun setImageList(imageList: List<Gallery>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.gallery_image)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ViewHolder, position: Int) {
        var imagesData = imageList[position]

        holder.image.setImageResource(imagesData.image)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}

//    var galleryArray = intArrayOf(
//        R.raw.image1,
//        R.raw.image2,
//        R.raw.image3,
//        R.raw.image4,
//        R.raw.image5,
//        R.raw.image6,
//        R.raw.image7,
//        R.raw.image8,
//        R.raw.image9,
//        R.raw.image10,
//        R.raw.image11,
//        R.raw.image12,
//        R.raw.image13,
//        R.raw.image14




