package com.kfd.jobana.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.kfd.jobana.R
import com.kfd.jobana.databinding.FragmentMainHostBinding

class MainHostFragment : Fragment() {
    private var _binding: FragmentMainHostBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomMenu: BottomNavigationView
    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainHostBinding.inflate(inflater, container, false)
        val view = binding.root

        viewPager = binding.viewPager

        bottomMenu = binding.bottomMenu
        viewPager.adapter = MainAdapter(parentFragmentManager, lifecycle)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomMenu.selectedItemId = R.id.mainFragment
                    else -> bottomMenu.selectedItemId = R.id.personalAccountFragment
                }
                super.onPageSelected(position)
            }
        })
        bottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.personalAccountFragment -> {
                    viewPager.currentItem = 1
                    true
                }
                else -> true
            }
        }

        return view
    }

}