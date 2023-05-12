package com.kfd.jobana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kfd.jobana.databinding.FragmentPersonalAccountBinding


class PersonalAccountFragment : Fragment() {

    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}