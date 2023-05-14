package com.kfd.jobana.ui.home

import android.app.Activity
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
import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.databinding.AdvertItemBinding
import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.models.Resource
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.viewmodels.AdvertViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.coroutineContext

class AdvertViewHolder(
    private val activity: Activity,
    private val context: Context,
    private val binding: AdvertItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(advert: AdvertResponse) {
        binding.apply {
            tvAdvertTitle.text = advert.title
            tvShortDescription.text = advert.shortDescription
            // TODO исправить это замечание с ценой потом
            tvPrice.text = "Цена: ${advert.price}р."
            if (advert.attachments.isEmpty()) {
                Log.d(TAG, "0")
                imvAdvert.setImageDrawable(context.resources.getDrawable(R.drawable.work))
            } else {

                val okHttpClient = OkHttpClient()
                val request = Request.Builder().url(Constants.BASE_URL + "files/" + advert.attachments[0]).also {
                    runBlocking {
                        val token = UserPreferences(context).authToken.first()
                        Log.d(TAG, "$token")
                        it.addHeader("Authorization", "Bearer $token")
                    }
                }.build()
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.use {
                            if (!response.isSuccessful) throw IOException("Unexpected code $response")

                            val bytes = response.body?.bytes()
                            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes!!.size)
                            if (bitmap != null) {
                                activity.runOnUiThread {
                                    imvAdvert.setImageBitmap(bitmap)
                                }
                            }

                        }
                    }
                })
            }

        }
    }
}
class AdvertsAdapter(
    private var activity: Activity,
    private var adverts: List<AdvertResponse>,
    private val context: Context,
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<AdvertViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertViewHolder {
        return AdvertViewHolder(activity, context, AdvertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = adverts.size

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
        val advert = adverts[position]

        holder.bind(advert)

        holder.itemView.setOnClickListener {
            onItemClick()
        }

    }
    fun updateAdapter(list: List<AdvertResponse>) {
        this.adverts = list
        notifyDataSetChanged()
    }
}