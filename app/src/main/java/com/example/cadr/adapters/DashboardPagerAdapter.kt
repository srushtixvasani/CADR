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
                return FragmentNews()
            }
            3 -> {
                return FragmentOpportunities()
            }
            4 -> {
                return FragmentGallery()
            }
            5 -> {
                return FragmentContactUs()
            }
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "ABOUT US"
            1 -> return "RESEARCH"
            2 -> return "NEWS"
            3 -> return "OPPORTUNITIES"
            4 -> return "GALLERY"
            5 -> return "CONTACT US"
        }
        return null!!
    }
    override fun getCount(): Int {
        return 6
    }
}