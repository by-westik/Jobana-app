package com.kfd.jobana

import android.content.ContentValues.TAG
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.kfd.jobana.databinding.FragmentLoginBinding
import com.kfd.jobana.databinding.FragmentSignUpBinding
import com.kfd.jobana.network.AuthApi
import com.kfd.jobana.network.LoginRequest
import com.kfd.jobana.network.RemoteDataSource
import com.kfd.jobana.network.Resource
import com.kfd.jobana.repository.AuthRepository
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val remoteDataSource = RemoteDataSource()
    private val authRepository =  AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))
    private lateinit var authViewModel: AuthViewModel

    private lateinit var btnLogin: MaterialButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        val factory = ViewModelFactory(authRepository)
        authViewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java )
        authViewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            authViewModel.login(LoginRequest(email, password))
        }

        return view
    }

}