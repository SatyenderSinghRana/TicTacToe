package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
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
        binding.tvsingle.animate().alpha(1f).translationY(1f).scaleX(1f).scaleY(1f).setDuration(600)
        binding.tvdouble.animate().alpha(1f).translationY(1f).scaleX(1f).scaleY(1f).setDuration(600)
        binding.tvexit.animate().alpha(1f).translationY(1f).scaleX(1f).scaleY(1f).setDuration(600)

        binding.tvsingle.setOnClickListener() {
            //val intent = Intent(this, SinglePlayerActivity::class.java)
            //startActivity(intent)
            Toast.makeText(
                applicationContext,
                "Coming Soon, Double player mode available",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.tvdouble.setOnClickListener() {
            val intent = Intent(this, DoublePlayerActivity::class.java)
            startActivity(intent)
        }

        //Exit App
        binding.tvexit.setOnClickListener() {
//            System.exit(-1)
            this.finishAffinity();
        }
    }
}