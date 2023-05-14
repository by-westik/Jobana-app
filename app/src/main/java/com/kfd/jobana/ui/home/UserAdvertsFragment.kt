package com.kfd.jobana.ui.home

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.R
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.databinding.FragmentPersonalAccountBinding
import com.kfd.jobana.databinding.FragmentUserAdvertsBinding
import com.kfd.jobana.models.Resource
import com.kfd.jobana.viewmodels.AdvertViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserAdvertsFragment : Fragment() {


    private var _binding: FragmentUserAdvertsBinding? = null
    private val binding get() = _binding!!

    private val advertViewModel: AdvertViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdvertsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserAdvertsBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recyclerViewUserAdverts
        setupRv()
        advertViewModel.getUserAdverts()

        return view
    }
    private fun click() {
        parentFragmentManager.beginTransaction().replace(R.id.fragmentMainContainerView, AdvertFragment()).commit()
    }

    private fun setupRv() {
        adapter = AdvertsAdapter(
            requireActivity(), listOf(), requireContext()
        ) { click() }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

     /*   advertViewModel.allAdvertResponse.observe(viewLifecycleOwner) { advertResponse ->
            when (advertResponse) {
                is Resource.Success -> {
                    adapter.updateAdapter(advertResponse.value)
                }
                else -> {}
            }
        }*/

        advertViewModel.response.observe(viewLifecycleOwner) {
            adapter.updateAdapter(it)
        }

    }

}