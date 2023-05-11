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
import com.kfd.jobana.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<AuthResponse> = MutableLiveData()
    val loginResponse: LiveData<AuthResponse>
        get() = _loginResponse

    //TODO сделать обработку ошибок сервера
    fun loginUser(loginRequest: LoginRequest) = viewModelScope.launch {
        repository.loginUser(loginRequest).let { response ->
             if (response.isSuccessful) {
                 _loginResponse.postValue(response.body())
             } else {
                 Log.d(TAG, "ERROR response code: ${response.code()}")
             }
        }
    }

    fun registerUser(registerRequest: RegisterRequest) = viewModelScope.launch {
        repository.registerUser(registerRequest).let {response ->
            if (response.isSuccessful) {
                _loginResponse.postValue(response.body())
            } else {
                Log.d(TAG, "ERROR response code: ${response.code()}")
            }
        }
    }


}