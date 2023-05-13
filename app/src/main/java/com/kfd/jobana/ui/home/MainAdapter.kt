package com.kfd.jobana.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList : ArrayList<Fragment> = createFragmentList()
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]

    private fun createFragmentList() = arrayListOf(MainFragment(), PersonalAccountFragment())

}