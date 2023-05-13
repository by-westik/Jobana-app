package com.kfd.jobana.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.R
import com.kfd.jobana.databinding.AdvertItemBinding
import com.kfd.jobana.models.Resource
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.viewmodels.AdvertViewModel


class AdvertViewHolder(
    private val context: Context,
    private val binding: AdvertItemBinding,
    private val viewModel: AdvertViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(advert: AdvertResponse) {
        binding.apply {
            tvAdvertTitle.text = advert.title
            tvShortDescription.text = advert.shortDescription
            // TODO исправить это замечание с ценой потом
            tvPrice.text = "Цена: ${advert.price}р."
            if (advert.attachments.isEmpty()) {
                imvAdvert.setImageDrawable(context.resources.getDrawable(R.drawable.work))
            } else {
                viewModel.attachments.observe(lifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                          //  Log.d(TAG, "${it.value.bytes()}")
                            val imageBytes = it.value.bytes()
                            imvAdvert.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size))
                        }
                        else -> {
                            Log.d(TAG, "fail")
                            Log.d(TAG, it.toString())
                            imvAdvert.setImageDrawable(context.resources.getDrawable(R.drawable.work))
                        }
                    }
                }

                viewModel.getAttachments(advert.attachments[0])
            }

        }
    }
}
class AdvertsAdapter(
    private var adverts: List<AdvertResponse>,
    private val context: Context,
    private val viewModel: AdvertViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<AdvertViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertViewHolder {
        return AdvertViewHolder(context, AdvertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewModel, lifecycleOwner)
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