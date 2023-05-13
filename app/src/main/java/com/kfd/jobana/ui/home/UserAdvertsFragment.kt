package com.kfd.jobana.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.R
import com.kfd.jobana.databinding.FragmentPersonalAccountBinding
import com.kfd.jobana.databinding.FragmentUserAdvertsBinding
import com.kfd.jobana.models.Resource
import com.kfd.jobana.viewmodels.AdvertViewModel
import dagger.hilt.android.AndroidEntryPoint

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

    private fun setupRv() {
        adapter = AdvertsAdapter(listOf())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        advertViewModel.allAdvertResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    adapter.updateAdapter(it.value)
                }
                else -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}