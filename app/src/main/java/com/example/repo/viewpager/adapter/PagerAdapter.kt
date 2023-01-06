package com.example.repo.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.repo.viewpager.page.ViewPagerEventFragment
import com.example.repo.viewpager.page.ViewPagerNKOFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            FIRST_POSITION -> ViewPagerEventFragment()
            else -> ViewPagerNKOFragment()
        }

    companion object{
        private const val FIRST_POSITION = 0
    }
}