package com.kfd.jobana.onboarding


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kfd.jobana.R

class OnBoardingViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle){

    private val fragmentList: ArrayList<Fragment> = createFragmentList()

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        val list = ArrayList<Fragment>  ()
        list.add(OnBoardingFragment.newInstance(context.resources.getString(R.string.title_1), context.resources.getString(R.string.description_1), R.drawable.on_boarding_image_1))
        list.add(OnBoardingFragment.newInstance(context.resources.getString(R.string.title_2), context.resources.getString(R.string.description_2), R.drawable.on_boarding_image_2))
        list.add(OnBoardingFragment.newInstance(context.resources.getString(R.string.title_3), context.resources.getString(R.string.description_3), R.drawable.on_boarding_image_3))
        return list
    }
}