package com.kfd.jobana.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.kfd.jobana.R
import com.kfd.jobana.databinding.FragmentMainHostBinding

class MainHostFragment : Fragment() {
    private var _binding: FragmentMainHostBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomMenu: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainHostBinding.inflate(inflater, container, false)
        val view = binding.root

        bottomMenu = binding.bottomMenu

        bottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> {
                    parentFragmentManager.beginTransaction().replace(R.id.fragmentMainContainerView, MainFragment()).commit()
                    true
                }
                R.id.personalAccountFragment -> {
                    parentFragmentManager.beginTransaction().replace(R.id.fragmentMainContainerView, PersonalAccountFragment()).commit()
                    true
                }
                else -> true
            }
        }

        return view
    }

}