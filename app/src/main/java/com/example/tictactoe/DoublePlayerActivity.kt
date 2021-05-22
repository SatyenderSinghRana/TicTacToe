package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityDoublePlayerBinding

class DoublePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoublePlayerBinding

    lateinit var bgMusic: MediaPlayer
    lateinit var winMusic: MediaPlayer
    lateinit var drawMusic: MediaPlayer
    lateinit var rollMusic: MediaPlayer

    val gameState = arrayListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var activePlayer = 0
    var gameActive = true
    var player = ""
    var counter = 0
    var musicEnabled = true

    val winningPositions = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoublePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bgMusic = MediaPlayer.create(applicationContext, R.raw.bgmusic)
        bgMusic.setVolume(0.4f, 0.4f)
        winMusic = MediaPlayer.create(applicationContext, R.raw.win)
        winMusic.setVolume(0.2f, 0.2f)
        rollMusic = MediaPlayer.create(applicationContext, R.raw.roll)
        rollMusic.setVolume(0.1f, 0.1f)
        drawMusic = MediaPlayer.create(applicationContext, R.raw.draw)
        drawMusic.setVolume(0.2f, 0.2f)
        playMusic()

        //animate gamearea
        binding.includedLayout.mainlayout.animate().alpha(1f).setDuration(2000)

        //display message
        binding.includedLayout.tvmessage.setText("X's goes first")

        binding.includedLayout.volume.setOnClickListener() {
            musicEnabled = if (!bgMusic.isPlaying) {
                binding.includedLayout.volume.setImageResource(R.drawable.ic_baseline_volume_up_24)
                playMusic()
                true
            } else {
                binding.includedLayout.volume.setImageResource(R.drawable.ic_baseline_volume_off_24)
                bgMusic.pause()
                false
            }
        }
    }

    fun checkTurn(view: View) {
        if (musicEnabled) rollMusic.start()

        val slot = view as TextView
        val tappedSlot = slot.tag.toString().toInt()

        if (gameState[tappedSlot] == 2 && gameActive) {
            gameState[tappedSlot] = activePlayer
            if (activePlayer == 0) {
                slot.setText("X")
                binding.includedLayout.tvmessage.setText("O's turn")
                activePlayer = 1
                counter++
            } else {
                slot.setText("O")
                binding.includedLayout.tvmessage.setText("X's turn")
                activePlayer = 0
                counter++
            }
        }
        slot.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(600)
        for (combo in winningPositions) {
            if (gameState[combo[0]] == gameState[combo[1]] &&
                gameState[combo[1]] == gameState[combo[2]] &&
                gameState[combo[0]] != 2
            ) {
                if (activePlayer == 0) {
                    player = "O"
                } else {
                    player = "X"
                }
                if (musicEnabled) {
                    bgMusic.stop()
                    winMusic.start()
                }
                counter = 0
                binding.includedLayout.tvmessage.setText("")
                binding.includedLayout.finalmessage.setText("$player has won")
                gameActive = false
                binding.includedLayout.optionwindow.visibility = View.VISIBLE
                binding.includedLayout.optionwindow.animate().alpha(1f).translationY(1f)
                    .setDuration(1000)
            }
        }
        if (counter == 9) {
            if (musicEnabled) {
                bgMusic.stop()
                drawMusic.start()
            }
            gameActive = false
            binding.includedLayout.tvmessage.setText("")
            binding.includedLayout.finalmessage.setText("Draw")
            binding.includedLayout.optionwindow.visibility = View.VISIBLE
            binding.includedLayout.optionwindow.animate().alpha(0.8f).translationY(1f)
                .setDuration(1000)
        }
    }

    fun newGame(view: View) {
        for (tappedSlot in 0..8) gameState[tappedSlot] = 2
        gameActive = true
        if (musicEnabled) {
            if (winMusic.isPlaying) winMusic.stop()
            if (drawMusic.isPlaying) drawMusic.stop()
            if (!bgMusic.isPlaying) playMusic()
        }
        counter = 0
        activePlayer = 0
        binding.includedLayout.tvmessage.setText("X goes first")
        binding.includedLayout.tv0.setText("")
        binding.includedLayout.tv1.setText("")
        binding.includedLayout.tv2.setText("")
        binding.includedLayout.tv3.setText("")
        binding.includedLayout.tv4.setText("")
        binding.includedLayout.tv5.setText("")
        binding.includedLayout.tv6.setText("")
        binding.includedLayout.tv7.setText("")
        binding.includedLayout.tv8.setText("")
        binding.includedLayout.optionwindow.visibility = View.GONE
    }

    fun closeGame(view: View) {
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun playMusic() {
        bgMusic.isLooping = true
        bgMusic.start()
    }

    override fun onResume() {
        super.onResume()
        bgMusic.start()
    }

    override fun onPause() {
        super.onPause()
        bgMusic.pause()
    }
}