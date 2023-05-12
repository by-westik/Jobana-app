package com.kfd.jobana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kfd.jobana.databinding.FragmentHomeBinding
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
                    loadFragment(MainFragment())
                    true
                }
                R.id.personalAccountFragment -> {
                    loadFragment(PersonalAccountFragment())
                    true
                }
                else -> true
            }
        }

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_nav_host_fragment, fragment)
            .commit()
    }
}