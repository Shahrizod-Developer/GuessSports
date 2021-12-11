package com.example.guesssports

import android.content.Context
import android.media.MediaPlayer

object Sound {
    lateinit var mediaPlayer: MediaPlayer

    fun create(context: Context){
        mediaPlayer=MediaPlayer.create(context,R.raw.menu_game)
    }
}