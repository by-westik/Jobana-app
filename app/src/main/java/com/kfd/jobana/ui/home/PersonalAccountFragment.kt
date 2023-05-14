package com.kfd.jobana.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.kfd.jobana.R
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.databinding.FragmentPersonalAccountBinding


class PersonalAccountFragment : Fragment() {

    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var userAdverts: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)

        userAdverts = binding.userAdverts
        userAdverts.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentMainContainerView, UserAdvertsFragment()).commit()
        }

        return binding.root
    }

}