package com.kfd.jobana.models

import com.google.gson.annotations.SerializedName


data class CloseRequest(
    @SerializedName("close")
    val isClosed: Boolean
)
