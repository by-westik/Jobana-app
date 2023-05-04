package com.kfd.jobana

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kfd.jobana.repository.AuthRepository
import com.kfd.jobana.repository.BaseRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
          return when {
              modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
              else -> throw IllegalArgumentException("ViewModelClass not found")
          }
    }
}