package com.kfd.jobana.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.R
import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.databinding.FragmentAdvertBinding
import com.kfd.jobana.databinding.FragmentUserAdvertsBinding
import com.kfd.jobana.viewmodels.AdvertViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdvertFragment : Fragment() {

    private var _binding: FragmentAdvertBinding? = null
    private val binding get() = _binding!!

    private val advertViewModel: AdvertViewModel by viewModels()

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDate: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdvertBinding.inflate(inflater, container, false)
        val view = binding.root

        tvTitle = binding.tvAdvertTitle
        tvDescription = binding.tvDescription
        tvPrice = binding.tvPrice
        recyclerView = binding.rvPhoto
        tvDate = binding.tvDate

        val bundle = this.arguments
        if (bundle != null) {
            Log.d(TAG, "NOT EMPTY")
            val id = bundle.getString("ID", "")
            setup()
            advertViewModel.getAdvertById(id)

        }
        return view
    }

    private fun setup() {
        adapter = ImageAdapter(listOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        advertViewModel.advertResponse.observe(viewLifecycleOwner) {
            updateUi(it)
        }

    }

    private fun updateUi(advert: AdvertItem) {
        tvTitle.text = advert.title
        tvDescription.text = advert.description
        tvDate.text = advert.createdAt
        tvPrice.text = "Цена: ${advert.price.toString()}р."
        adapter.updateAdapter(advert.attachments)
    }

}