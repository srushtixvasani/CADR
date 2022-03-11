package com.example.cadr.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cadr.fragments.FragmentServices
import com.example.cadr.fragments.FragmentUsers


class DashboardPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return FragmentUsers()
            }
            1 -> {
                return FragmentServices()
            }
        }
        return null!!
    }

    override fun getCount(): Int {
        return 2
    }
}