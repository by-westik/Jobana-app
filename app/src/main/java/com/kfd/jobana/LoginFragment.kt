package com.kfd.jobana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.databinding.FragmentLoginBinding
import com.kfd.jobana.models.LoginRequest
import com.kfd.jobana.models.Resource
import com.kfd.jobana.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var userPreferences: UserPreferences
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvSignUp: AppCompatTextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        authViewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        userPreferences.saveUserAuthToken(it.value.token)
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_mainHostFragment)
                }
                else -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }

        btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            authViewModel.loginUser(LoginRequest(email, password))
        }

        tvSignUp = binding.signupTextview
        tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return view
    }

}