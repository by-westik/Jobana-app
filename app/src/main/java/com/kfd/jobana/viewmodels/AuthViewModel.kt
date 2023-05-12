package com.kfd.jobana.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.jobana.models.LoginRequest
import com.kfd.jobana.models.AuthResponse
import com.kfd.jobana.models.RegisterRequest
import com.kfd.jobana.models.Resource
import com.kfd.jobana.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<AuthResponse>>
        get() = _loginResponse

    fun loginUser(loginRequest: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = repository.loginUser(loginRequest)
    }

    fun registerUser(registerRequest: RegisterRequest) = viewModelScope.launch {
        _loginResponse.value = repository.registerUser(registerRequest)
    }


}