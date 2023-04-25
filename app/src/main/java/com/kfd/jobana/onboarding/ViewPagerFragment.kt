package com.kfd.jobana.onboarding

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kfd.jobana.R
import com.kfd.jobana.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var tvSkip: AppCompatTextView
    private lateinit var tvNext: AppCompatTextView

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        viewPager2 = binding.viewPager
        viewPager2.adapter = OnBoardingViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.pageIndicator, viewPager2) { _, _ -> }.attach()


        tvNext = binding.tvNext
        Log.d("vpfargment", "before onclicklistener")
        tvNext.setOnClickListener {
            if (getItem() > viewPager2.childCount) {
                findNavController().navigate(R.id.action_viewPagerFragment_to_finishFragment)
                onBoardingFinished()
            } else {
                viewPager2.setCurrentItem(getItem() + 1, true)
            }
        }

        tvSkip = binding.tvSkip
        tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            onBoardingFinished()
        }

        return view
    }

    private fun getItem(): Int {
        return viewPager2.currentItem
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}