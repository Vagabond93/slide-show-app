package com.emonized.birthday.listener

interface PlayBackInfoListener {
    fun onCompletedSong()

    fun onReset()

    fun onLogUpdated(message : String)

    fun onPlay()

    fun onPause()
}