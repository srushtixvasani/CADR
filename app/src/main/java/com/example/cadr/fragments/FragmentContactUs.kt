package com.example.cadr.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cadr.R
import com.google.android.material.button.MaterialButton


class FragmentContactUs : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // send email to CADR
        var emailBtn = view.findViewById<MaterialButton>(R.id.emailUs)
        emailBtn.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:")
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("cadr@swansea.co.uk"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Contact Us Query")
            if (email.resolveActivity(activity!!.packageManager) != null) {
                startActivity(email)
            }
        }

        //call CADR
        var phoneBtn = view.findViewById<MaterialButton>(R.id.phoneUs)
        phoneBtn.setOnClickListener {
            val phone = Intent(Intent.ACTION_DIAL)
            phone.data = Uri.parse("tel:01792 295099")
            startActivity(phone)
        }

        // go to website
        var websiteBtn = view.findViewById<MaterialButton>(R.id.website)
        websiteBtn.setOnClickListener {
            val website = Intent(Intent.ACTION_VIEW)
            website.data = Uri.parse("https://www.cadr.cymru/en/")
            startActivity(website)
        }

        // go to facebook
        var facebookBtn = view.findViewById<MaterialButton>(R.id.facebook)
        facebookBtn.setOnClickListener {
            val facebook = Intent(Intent.ACTION_VIEW)
            facebook.data = Uri.parse("https://www.facebook.com/CADRCymru")
            startActivity(facebook)

        }


        // go to twitter
        var twitterBtn = view.findViewById<MaterialButton>(R.id.twitter)
        twitterBtn.setOnClickListener {
            val twitter = Intent(Intent.ACTION_VIEW)
            twitter.data = Uri.parse("https://twitter.com/CadrProgramme")
            startActivity(twitter)
        }
    }


}