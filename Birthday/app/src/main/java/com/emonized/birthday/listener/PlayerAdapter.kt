package com.emonized.birthday.listener

interface PlayerAdapter {
    fun loadMedia(resourceId: Int)

    fun release()

    fun isPlaying(): Boolean

    fun play()

    fun reset()

    fun pause()

}