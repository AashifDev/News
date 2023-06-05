package com.example.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news.Constant.TOTAL_NEWS_TAB
import com.example.news.fragment.BusinessFragment
import com.example.news.fragment.EntertainmentFragment
import com.example.news.fragment.SportFragment
import com.example.news.fragment.TechnologyFragment

class TabLayoutAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle){

    override fun getItemCount(): Int = TOTAL_NEWS_TAB

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> {
                return BusinessFragment()
            }
            1 -> {
                return TechnologyFragment()
            }
            2 -> {
                return EntertainmentFragment()
            }
            3 -> {
                return SportFragment()
            }

            else -> return BusinessFragment()

        }
    }

}