package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tictactoe.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTic.animate().alpha(1f).setDuration(1000)
        binding.tvTac.animate().alpha(1f).setStartDelay(800).setDuration(1000)
        binding.tvToe.animate().alpha(1f).setStartDelay(1800).setDuration(1000)

        splashscreen()
    }

    private fun splashscreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}