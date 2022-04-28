package com.example.cadr.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cadr.R
import com.example.cadr.adapters.GalleryAdapter
import com.example.cadr.models.Gallery


class FragmentGallery : Fragment() {


    private lateinit var recyclerView : RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter
    private var imageList = mutableListOf<Gallery>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.gallery_recycler_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )

        galleryAdapter = GalleryAdapter(this.requireContext())
        recyclerView.adapter = galleryAdapter

//        imageList.add(Gallery(R.raw.image1))
        imageList.add(Gallery(R.raw.image5))
        imageList.add(Gallery(R.raw.image7))
        imageList.add(Gallery(R.raw.image8))

        galleryAdapter.setImageList(imageList)

    }





}