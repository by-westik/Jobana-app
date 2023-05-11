package com.kfd.jobana


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kfd.jobana.data.UserPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val userPreferences = UserPreferences(this)
        Toast.makeText(this, "${userPreferences.authToken}", Toast.LENGTH_LONG).show()
        setContentView(R.layout.activity_main)

    }
}
