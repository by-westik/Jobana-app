package com.kfd.jobana.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.models.Resource
import com.kfd.jobana.repository.AdvertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvertViewModel @Inject constructor(
    private val repository: AdvertRepository
) : ViewModel() {

    private val _allAdvertResponse: MutableLiveData<Resource<List<AdvertResponse>>> = MutableLiveData()
    val allAdvertResponse: LiveData<Resource<List<AdvertResponse>>>
        get() = _allAdvertResponse

    fun getUserAdverts()  = viewModelScope.launch {
        _allAdvertResponse.value = repository.getUserAdverts()
    }

}