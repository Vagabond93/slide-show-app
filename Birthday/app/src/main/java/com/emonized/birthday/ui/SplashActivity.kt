package com.emonized.birthday.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.emonized.birthday.R
import com.emonized.birthday.factory.MediaPlayerHolder
import com.emonized.birthday.listener.PlayBackInfoListener

class SplashActivity : AppCompatActivity(){

    lateinit var mediaPlayerHolder: MediaPlayerHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initializePlayBackController()
    }




    private fun initializePlayBackController() {
        mediaPlayerHolder = MediaPlayerHolder(this)
        mediaPlayerHolder.playBackInfoListener = PlayBackInfoListenerImpl()
        mediaPlayerHolder.loadMedia(R.raw.happy_birthday_song)


    }

    override fun onResume() {
        super.onResume()
        mediaPlayerHolder.play()
    }

    inner class PlayBackInfoListenerImpl : PlayBackInfoListener {
        override fun onPlay() {

        }

        override fun onPause() {

        }

        override fun onCompletedSong() {

            startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            finish()

        }

        override fun onReset() {

        }

        override fun onLogUpdated(message: String) {
            Log.d(GalleryActivity.TAG, message)
        }

    }
}