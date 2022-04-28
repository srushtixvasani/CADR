package com.example.cadr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.cadr.R


class FragmentNews : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsWebView = view!!.findViewById<WebView>(R.id.newsWebView)
        newsWebView.webViewClient = WebViewClient()
        newsWebView.loadUrl("https://www.cadr.cymru/en/news.htm")
        newsWebView.settings.javaScriptEnabled = true
        newsWebView.settings.setSupportZoom(true)

    }
}