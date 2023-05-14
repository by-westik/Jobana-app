package com.kfd.jobana.data

import com.kfd.jobana.models.responses.AdvertResponse
import okhttp3.ResponseBody
import java.io.InputStream

data class AdvertItem(
    val advert: AdvertResponse,
    val body: ResponseBody
)