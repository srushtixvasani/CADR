package com.example.cadr.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cadr.fragments.*



class DashboardPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return FragmentAboutUs()
            }
            1 -> {
                return FragmentResearch()
            }
            2 -> {
                return FragmentGallery()
            }
            3 -> {
                return FragmentNews()
            }
            4 -> {
                return FragmentNewsletter()
            }
            5 -> {
                return FragmentOpportunities()
            }
            6 -> {
                return FragmentContactUs()
            }
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "ABOUT US"
            1 -> return "RESEARCH"
            2 -> return "GALLERY"
            3 -> return "NEWS"
            4 -> return "NEWSLETTER"
            5 -> return "OPPORTUNITIES"
            6 -> return "CONTACT US"
        }
        return null!!
    }
    override fun getCount(): Int {
        return 7
    }
}