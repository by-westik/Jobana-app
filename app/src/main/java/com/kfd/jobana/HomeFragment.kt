package com.kfd.jobana

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (onBoardingFinished()) {
            Toast.makeText(context, "The screens have already been viewed", Toast.LENGTH_SHORT).show()
           // findNavController().navigate(R.id.action_homeFragment_to_onboardingViewPagerFragment)
        } else {
            Toast.makeText(context, "First viewing of screens", Toast.LENGTH_SHORT).show()
          //  findNavController().navigate(R.id.action_homeFragment_to_onboardingViewPagerFragment)
        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding",
            Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

}