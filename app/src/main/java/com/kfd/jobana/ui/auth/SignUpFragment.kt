package com.kfd.jobana.ui.auth

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.kfd.jobana.R
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.databinding.FragmentSignUpBinding
import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.models.requests.RegisterRequest
import com.kfd.jobana.models.Resource
import com.kfd.jobana.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern

// TODO добавить валидацию данных в регистарции и авторизации
// TODO вынести дату из фрагмента в хелпер

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var userPreferences: UserPreferences
    private lateinit var btnSignUp: MaterialButton
    private lateinit var tvLogin: AppCompatTextView
    private lateinit var tvDate: TextInputEditText
    private lateinit var tvEmail: TextInputEditText

    private var calendar = Calendar.getInstance()

    private val textWatcher = object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "ddmmyyyy"

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() != current) {
                    var clean = p0.toString().replace("[^\\d.]|\\.".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]|\\.", "")

                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 6) {
                        sel++
                        i += 2
                    }
                    if (clean == cleanC) sel--

                    if (clean.length < 8) {
                        clean += ddmmyyyy.substring(clean.length)
                    } else {
                        val day = Integer.parseInt(clean.substring(0, 2))
                        val mon = Integer.parseInt(clean.substring(2, 4))
                        val year = Integer.parseInt(clean.substring(4, 8))

                        clean = String.format("%02d%02d%02d", day, mon, year)
                    }

                    clean = String.format("%s-%s-%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8))

                    sel = if (sel < 0) 0 else sel
                    current = clean
                    tvDate.setText(current)
                    tvDate.setSelection(if (sel < current.count()) sel else current.count())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable) {}
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        userPreferences = UserPreferences(requireContext())

        authViewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        userPreferences.saveUserAuthToken(it.value.token)
                    }
                    findNavController().navigate(R.id.action_signUpFragment_to_mainHostFragment)
                }
                else -> {
                  //  Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        btnSignUp = binding.signUpBtn
        btnSignUp.setOnClickListener {
            val firstName = binding.firstNameText.text.toString()
            val surname = binding.surnameText.text.toString()
            val genderId = when (binding.genderRadioGroup.checkedRadioButtonId) {
                R.id.male_radio_button -> Constants.GENDER_MALE
                else -> Constants.GENDER_FEMALE
            }
            val date = binding.dateText.text.toString()
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            authViewModel.registerUser(RegisterRequest(email, password, firstName, surname, date, genderId))
        }

        tvLogin = binding.signInTextView
        tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }

        tvDate = binding.dateText
        tvDate.addTextChangedListener(textWatcher)

        tvDate.setOnClickListener {
            DatePickerDialog(requireContext(), R.style.CustomDatePickerDialog, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        return view
    }

    private fun updateDate() {
        val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
        tvDate.setText(sdf.format(calendar.time))
    }


    private fun isValidEmail(email: String) : Boolean {
        if (email.isBlank()) return false
        val pat = Pattern.compile(Constants.EMAIL_REGEX)
        return pat.matcher(email).matches()
    }
}