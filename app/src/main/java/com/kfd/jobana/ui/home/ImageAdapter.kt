package com.kfd.jobana.ui.home

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfd.jobana.databinding.AdvertItemBinding
import com.kfd.jobana.databinding.ImageItemBinding


class ImageViewHolder(
    private val binding: ImageItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(img: ByteArray) {
        val bitmap = BitmapFactory.decodeByteArray(img, 0, img.size)
        if (bitmap != null) {
            binding.imvAdvert.setImageBitmap(bitmap)
        }
    }

}
class ImageAdapter(
    private var list: List<ByteArray>
) : RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val img = list[position]
        holder.bind(img)

    }
    fun updateAdapter(imgs: List<ByteArray>) {
        this.list = imgs
        notifyDataSetChanged()
    }

}