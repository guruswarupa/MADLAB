package com.guruswarupa.lab7

import android.os.Bundle
import android.media.MediaPlayer
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.buttonPlay)
        val pauseButton = findViewById<Button>(R.id.buttonPause)
        val forwardButton = findViewById<Button>(R.id.buttonForward)
        val backwardButton = findViewById<Button>(R.id.buttonBackward)
        seekBar = findViewById(R.id.seekBar)

        // Audio file placed in res/raw folder (example: song.mp3)
        mediaPlayer = MediaPlayer.create(this, R.raw.song)

        mediaPlayer?.let {
            seekBar.max = it.duration
        }

        playButton.setOnClickListener {
            mediaPlayer?.start()
        }

        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }

        forwardButton.setOnClickListener {
            mediaPlayer?.let {
                val currentPosition = it.currentPosition
                it.seekTo(currentPosition + 5000) // forward 5 sec
            }
        }

        backwardButton.setOnClickListener {
            mediaPlayer?.let {
                val currentPosition = it.currentPosition
                it.seekTo(currentPosition - 5000) // backward 5 sec
            }
        }

        // Update SeekBar continuously
        Thread {
            while (mediaPlayer != null) {
                try {
                    if (mediaPlayer?.isPlaying == true) {
                        seekBar.progress = mediaPlayer?.currentPosition ?: 0
                    }
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()

        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPlayer?.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            },
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
