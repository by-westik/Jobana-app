package com.kfd.jobana

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.network.LoginRequest
import com.kfd.jobana.network.Resource
import com.kfd.jobana.network.responses.LoginResponse
import com.kfd.jobana.repository.AuthRepository
import com.kfd.jobana.repository.BaseRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = repository.loginUser(email, password)
    }

    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = repository.login(loginRequest)
    }

}