package com.kfd.jobana.viewmodels

import android.content.ClipData
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.models.Resource
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.repository.AdvertRepository
import com.kfd.jobana.repository.AttachmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject



@HiltViewModel
class AdvertViewModel @Inject constructor(
    private val advertRepository: AdvertRepository,
    private val attachmentRepository: AttachmentRepository
) : ViewModel() {

    private val _allAdvertResponse: MutableLiveData<Resource<List<AdvertResponse>>> = MutableLiveData()
    val allAdvertResponse: LiveData<Resource<List<AdvertResponse>>>
        get() = _allAdvertResponse

    init {
        viewModelScope.launch {
            _allAdvertResponse.value = advertRepository.getUserAdverts()
        }
    }
    fun getUserAdverts() = viewModelScope.launch {
        _allAdvertResponse.value = advertRepository.getUserAdverts()
    }

    fun getAttachments(id: String? = null) = id?.let {attachmentRepository.getAttachment(id) }


}

