package com.kfd.jobana.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.models.Resource
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.repository.AdvertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class AdvertViewModel @Inject constructor(
    private val advertRepository: AdvertRepository
) : ViewModel() {

    private val _response: MutableLiveData<List<AdvertItem>> = MutableLiveData()
    val response: LiveData<List<AdvertItem>>
        get() = _response

    fun getUserAdverts() = viewModelScope.launch{
        advertRepository.getUserAdverts({
            _response.postValue(it)
        }, { error ->
            Log.d(TAG, "User $error")
        })

    }

    fun getAllAdverts() = viewModelScope.launch {
        advertRepository.getAllAdverts({
            _response.postValue(it)
        }, { error ->
            Log.d(TAG, "All $error")
        })
    }


}

