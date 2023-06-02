package com.kfd.jobana.ui.home

import android.content.ClipDescription
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.kfd.jobana.R
import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.databinding.FragmentEditAdvertBinding
import com.kfd.jobana.databinding.FragmentUserAdvertsBinding
import com.kfd.jobana.viewmodels.AdvertViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class EditAdvertFragment : Fragment() {

    private var _binding: FragmentEditAdvertBinding? = null
    private val binding get() = _binding!!

    private val advertViewModel: AdvertViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter

    private lateinit var tvTitle: TextInputEditText
    private lateinit var tvShortDescription: TextInputEditText
    private lateinit var tvDescription: TextInputEditText
    private lateinit var tvPrice: TextInputEditText
    private lateinit var tvCity: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditAdvertBinding.inflate(inflater, container, false)
        val view = binding.root


        tvTitle = binding.titleText
        tvShortDescription = binding.shortDescriptionText
        tvDescription = binding.descriptionText
        tvPrice = binding.priceText
        recyclerView = binding.rvPhoto
        tvCity = binding.autoCompleteTextView

        val bundle = this.arguments
        if (bundle != null) {
        //    binding.btnRespond.text = "Изменить"
            Log.d(ContentValues.TAG, "NOT EMPTY")
            val id = bundle.getString("ID", "")
            setup()
            advertViewModel.getAdvertById(id)
        }

        val items = listOf("Москва", "Самара", "Тюмень", "Бишкек")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.city_item, items)
        tvCity.setAdapter(arrayAdapter)

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
        tvTitle.setText(advert.title)
        tvShortDescription.setText(advert.shortDescription)
        tvDescription.setText(advert.description)
        tvPrice.setText(advert.price.toString())
        adapter.updateAdapter(advert.attachments)
        tvCity.setText(advert.city)

    }
}