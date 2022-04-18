package com.example.cadr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cadr.R


/**
 * A simple [Fragment] subclass.
 * Use the [NewsletterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsletterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newsletter, container, false)
    }

}