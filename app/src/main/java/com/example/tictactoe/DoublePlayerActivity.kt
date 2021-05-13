package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityDoublePlayerBinding

class DoublePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoublePlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoublePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainlayout.animate().alpha(1f).setDuration(500)
    }
}