package com.kfd.jobana.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.R
import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.databinding.FragmentMainBinding
import com.kfd.jobana.models.Resource
import com.kfd.jobana.viewmodels.AdvertViewModel
import com.kfd.jobana.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdvertsAdapter

    private val advertViewModel: AdvertViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        recyclerView = binding.rvAllAdverts
        setupRv()
        advertViewModel.getAllAdverts()


        return binding.root
    }

    private fun click(advertId: String) {
        val advertFragment = AdvertFragment()
        val id = Bundle()
        id.putString("ID", advertId)
        advertFragment.arguments = id
        parentFragmentManager.beginTransaction().replace(R.id.fragmentMainContainerView, advertFragment).commit()
    }

    private fun setupRv() {
        val advertClick = {advert: AdvertItem -> click(advert.id)}
        adapter = AdvertsAdapter(requireActivity(), listOf(), requireContext(), advertClick)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        advertViewModel.response.observe(viewLifecycleOwner) {
            adapter.updateAdapter(it)
        }
    }

}