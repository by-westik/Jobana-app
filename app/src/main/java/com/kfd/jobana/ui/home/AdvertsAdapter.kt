package com.kfd.jobana.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.databinding.AdvertItemBinding
import com.kfd.jobana.models.responses.AdvertResponse


class AdvertViewHolder(private val binding: AdvertItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(advert: AdvertResponse) {
        binding.apply {
            tvAdvertTitle.text = advert.title
            tvShortDescription.text = advert.shortDescription
            // TODO исправить это замечание с ценой потом
            tvPrice.text = "Цена: ${advert.price.toString()}р."

        }
    }
}
class AdvertsAdapter(private var adverts: List<AdvertResponse>) : RecyclerView.Adapter<AdvertViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertViewHolder {
        return AdvertViewHolder(AdvertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = adverts.size

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
        val advert = adverts[position]
        holder.bind(advert)
    }

    fun updateAdapter(list: List<AdvertResponse>) {
        this.adverts = list
        notifyDataSetChanged()
    }
}