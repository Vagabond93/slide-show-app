package com.emonized.birthday.factory

import android.content.Context
import android.media.MediaPlayer
import com.emonized.birthday.listener.PlayBackInfoListener
import com.emonized.birthday.listener.PlayerAdapter

class MediaPlayerHolder(val context: Context) : PlayerAdapter {

    private var mediaPlayer: MediaPlayer? = null
    var playBackInfoListener: PlayBackInfoListener? = null
    var mediaResId = 0


    override fun loadMedia(resourceId: Int) {
        mediaResId = resourceId
        initializeMediaPlayer()

        val assetFileDescriptor = context.resources.openRawResourceFd(mediaResId)

        try {
            mediaPlayer?.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )

        } catch (ex: Exception) {
            logToUI("mediaPlayer cannot set media file as source")
        }

        assetFileDescriptor.close()
        try {
            mediaPlayer?.prepare()
        } catch (ex: Exception) {
            logToUI("mediaPlayer cannot be prepared")
        }

    }


    private fun initializeMediaPlayer() {
        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer()
            logToUI("MediaPlayer Created")
            mediaPlayer?.setOnCompletionListener {
                playBackInfoListener?.onCompletedSong()
            }
        }
    }


    override fun release() {
        logToUI("Released media player")
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    override fun play() {
        if (!isPlaying()) {
            mediaPlayer?.start()
        }
    }

    override fun reset() {
        mediaPlayer?.reset()
    }

    override fun pause() {
        if (isPlaying()) {
            mediaPlayer?.pause()
        }
    }

    private fun logToUI(message: String) {
        playBackInfoListener?.onLogUpdated(message)
    }
}