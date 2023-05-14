package com.kfd.jobana.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.models.Resource
import com.kfd.jobana.models.requests.UserRequest
import com.kfd.jobana.models.responses.AuthResponse
import com.kfd.jobana.models.responses.UserResponse
import com.kfd.jobana.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userInfo: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val userInfo: LiveData<Resource<UserResponse>>
        get() = _userInfo

    private val _userImage: MutableLiveData<Resource<ResponseBody>> = MutableLiveData()
    val userImage: LiveData<Resource<ResponseBody>>
        get() = _userImage

    init {
        viewModelScope.launch {
            _userInfo.value = userRepository.getUser()
            _userImage.value = userRepository.getUserImage()
        }
    }

    fun changeUserProfile(userRequest: UserRequest) = viewModelScope.launch {
        _userInfo.value = userRepository.changeUserProfile(userRequest)
    }

}