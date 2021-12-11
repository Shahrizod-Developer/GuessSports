package com.example.guesssports

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null
    lateinit var backPress: () -> Unit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Sound.create(this)
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release();
    }

    fun play()
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.menu_game)
        mediaPlayer?.start();
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer=null
//        mediaPlayer?.release();
    }
    private var doubleBackToExitPressedOnce = false
    private val mHandler: Handler? = Handler()

    private val mRunnable =
        Runnable { doubleBackToExitPressedOnce = false }

    override fun onDestroy() {
        super.onDestroy()
        mHandler?.removeCallbacks(mRunnable)
    }

    override fun onBackPressed() {

        backPress()
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        mHandler?.postDelayed(mRunnable, 2000)
    }
}