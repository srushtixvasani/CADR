package com.example.cadr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ScrollView
import com.example.cadr.R


class FragmentOpportunities : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opportunities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val opportunityScrollView = view.findViewById<ScrollView>(R.id.opportunity_scrollView)
        opportunityScrollView.post(Runnable { opportunityScrollView.scrollTo(0, 0) })


        val opportunitiesWebView = view.findViewById<WebView>(R.id.opportunitiesWebView)
        opportunitiesWebView.webViewClient = WebViewClient()
        opportunitiesWebView.loadUrl("https://www.cadr.cymru/en/opportunities.htm")
        opportunitiesWebView.settings.javaScriptEnabled = true
        opportunitiesWebView.settings.setSupportZoom(true)

    }


}