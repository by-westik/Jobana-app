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
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.kfd.jobana.databinding.FragmentLoginBinding
import com.kfd.jobana.databinding.FragmentSignUpBinding
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

    private lateinit var btnLogin: MaterialButton
    val URL = "http://192.168.0.102:8000/api/health"
    var okHttpClient: OkHttpClient = OkHttpClient()

    private fun loadHealth() {
        val request: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json = response.body?.string()
                    val txt = (json?.let { JSONObject(it).get("message") }).toString()

                    Log.d(TAG, txt)

                }
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            loadHealth()
        }

        return view
    }

}