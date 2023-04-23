package com.kfd.jobana.onboarding

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kfd.jobana.R

class OnBoardingViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val context: Context
) : FragmentStateAdapter(fragmentManager, lifecycle){

    private val fragmentList: ArrayList<Fragment> = createFragmentList()

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        val list = ArrayList<Fragment> (3)
        list[0] = OnBoardingFragment.newInstance("First Title", "First Description", R.drawable.ic_baseline_chat_24)
        list[1] = OnBoardingFragment.newInstance("Second title", "Second Description", R.drawable.ic_baseline_add_to_home_screen_24)
        list[2] = OnBoardingFragment.newInstance("Third title", "Third description", R.drawable.ic_baseline_monetization_on_24)

        return list
    }
}