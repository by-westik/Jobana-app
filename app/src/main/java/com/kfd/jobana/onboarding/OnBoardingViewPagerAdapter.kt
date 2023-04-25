package com.kfd.jobana.onboarding

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kfd.jobana.R

class OnBoardingViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle){

    private val fragmentList: ArrayList<Fragment> = createFragmentList()

    override fun createFragment(position: Int): Fragment {
        Log.d("adapter", "create Fragment")
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        val list = ArrayList<Fragment>  ()
        list.add(OnBoardingFragment.newInstance("First Title", "First Description", R.drawable.on_boarding_image_1))
        list.add(OnBoardingFragment.newInstance("Second title", "Second Description", R.drawable.ic_baseline_add_to_home_screen_24))
        list.add(OnBoardingFragment.newInstance("Third title", "Third description", R.drawable.ic_baseline_monetization_on_24))

        return list
    }
}