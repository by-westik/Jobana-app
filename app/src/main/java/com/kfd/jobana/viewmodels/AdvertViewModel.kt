package com.kfd.jobana.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.models.Resource
import com.kfd.jobana.repository.AdvertRepository
import com.kfd.jobana.repository.AttachmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class AdvertViewModel @Inject constructor(
    private val advertRepository: AdvertRepository,
    private val attachmentRepository: AttachmentRepository
) : ViewModel() {

    private val _allAdvertResponse: MutableLiveData<Resource<List<AdvertResponse>>> = MutableLiveData()
    val allAdvertResponse: LiveData<Resource<List<AdvertResponse>>>
        get() = _allAdvertResponse

    private val _attachments: MutableLiveData<Resource<ResponseBody>> = MutableLiveData()
    val attachments: LiveData<Resource<ResponseBody>>
        get() = _attachments

    fun getUserAdverts() = viewModelScope.launch {
        _allAdvertResponse.value = advertRepository.getUserAdverts()
    }

    fun getAttachments(id: String) = viewModelScope.launch {
        Log.d(TAG, "id = $id")
        _attachments.value = attachmentRepository.getAttachment(id)
    }

}