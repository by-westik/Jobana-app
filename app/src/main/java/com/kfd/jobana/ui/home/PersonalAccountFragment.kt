package com.kfd.jobana.ui.home

import android.content.ContentValues.TAG
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kfd.jobana.R
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.databinding.FragmentPersonalAccountBinding
import com.kfd.jobana.models.Resource
import com.kfd.jobana.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalAccountFragment : Fragment() {

    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var userAdverts: LinearLayout
    private val userViewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)

        userAdverts = binding.userAdverts
        userAdverts.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentMainContainerView, UserAdvertsFragment()).commit()
        }

        setupUserInfo()

        return binding.root
    }

    private fun setupUserInfo() {
        userViewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.tvFio.text = it.value.firstName + " " + it.value.lastName
                    binding.tvEmail.text =  it.value.email
                }
                else -> {
                    Log.d(TAG, "INFO ERROR")
                }
            }
        }

        userViewModel.userImage.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val byteArray = it.value.bytes()
                    binding.imvProfile.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
                }
                else -> {
                    Log.d(TAG, "IMAGE ERROR")
                }
            }
        }
    }

}