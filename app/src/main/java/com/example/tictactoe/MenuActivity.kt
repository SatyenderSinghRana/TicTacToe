package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set animation when activity starts
        binding.tvsingle.animate().alpha(1f).translationX(0f).setDuration(1000)
        binding.tvdouble.animate().alpha(1f).translationX(0f).setDuration(1000)
        binding.tvexit.animate().alpha(1f).translationY(0f).setDuration(1000)

        binding.tvsingle.setOnClickListener() {
            val intent = Intent(this, SinglePlayerActivity::class.java)
            startActivity(intent)
        }

        binding.tvdouble.setOnClickListener() {
            val intent = Intent(this, DoublePlayerActivity::class.java)
            startActivity(intent)
        }

        //Exit App
        binding.tvexit.setOnClickListener() {
            Toast.makeText(applicationContext,"Closing app",Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                System.exit(-1)
            }, 1000)
        }
    }
}