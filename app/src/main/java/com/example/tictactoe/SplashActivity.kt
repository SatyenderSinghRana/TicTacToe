package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings = getSharedPreferences("prefs", 0)
        var count = settings.getInt("count", 0)
        if (count == 0 || count == 5) {
            val editor = settings.edit()
            count++
            editor.putInt("count", count)
            editor.apply()
            splashscreen()
        } else {
            count++
            if (count == 5) count = 0
            val editor = settings.edit()
            editor.putInt("count", count)
            editor.apply()

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun splashscreen() {
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.intro)
        mediaPlayer.start()

        binding.tvTic.animate().alpha(1f).setDuration(1000)
        binding.tvTac.animate().alpha(1f).setStartDelay(800).setDuration(1000)
        binding.tvToe.animate().alpha(1f).setStartDelay(1800).setDuration(1000)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, 4200)
    }
}