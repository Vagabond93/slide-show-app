package com.emonized.birthday.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.emonized.birthday.R
import com.emonized.birthday.adapter.GalleryAdapter
import com.emonized.birthday.constant.AppConstant
import com.emonized.birthday.constant.AppConstant.Companion.GALLERY_SCROLL_SPEED
import com.emonized.birthday.databinding.ActivityGalleryBinding
import com.emonized.birthday.factory.MediaPlayerHolder
import com.emonized.birthday.listener.PlayBackInfoListener
import com.emonized.birthday.util.UIUtil
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(){


    companion object{
        const val TAG = "GalleryActivity"
    }

    lateinit var viewBinding: ActivityGalleryBinding
    lateinit var galleryAdapter: GalleryAdapter
    lateinit var mediaPlayerHolder: MediaPlayerHolder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)

        initView()

    }

    private fun initView() {

        galleryAdapter = GalleryAdapter(this, UIUtil.getImageIdList().toMutableList())
        gallery_rv.layoutManager = getCustomGridLayoutManager()
        gallery_rv.adapter = galleryAdapter

        initializePlayBackController()

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    fun initializePlayBackController() {
        mediaPlayerHolder = MediaPlayerHolder(this)
        mediaPlayerHolder.playBackInfoListener = PlayBackInfoListenerImpl()
        mediaPlayerHolder.loadMedia(R.raw.merged_song)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "On Start Called")
    }

    override fun onResume() {
        super.onResume()
        mediaPlayerHolder.play()

        gallery_rv.smoothScrollToPosition(galleryAdapter.getItemSize() - 1)

        gallery_rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                gallery_rv.smoothScrollToPosition(galleryAdapter.getItemSize()-1)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        mediaPlayerHolder.pause()
    }

    override fun onDestroy() {
        mediaPlayerHolder.release()
        Log.d(TAG, "On Destroy called")
        super.onDestroy()

    }

    private fun getCustomGridLayoutManager() : GridLayoutManager{
        var gridLayoutManager = object : GridLayoutManager(this, 2, HORIZONTAL, false) {
            override fun smoothScrollToPosition(
                recyclerView: RecyclerView?,
                state: RecyclerView.State?,
                position: Int
            ) {
                var smoothScroller = object : LinearSmoothScroller(this@GalleryActivity) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                        return GALLERY_SCROLL_SPEED / displayMetrics!!.densityDpi
                    }

                }

                smoothScroller.targetPosition = position
                startSmoothScroll(smoothScroller)
            }
        }

        gridLayoutManager.isSmoothScrollbarEnabled = true

        return gridLayoutManager
    }

    inner class PlayBackInfoListenerImpl : PlayBackInfoListener{
        override fun onPlay() {

        }

        override fun onPause() {

        }

        override fun onCompletedSong() {
            mediaPlayerHolder.loadMedia(R.raw.merged_song)
            mediaPlayerHolder.play()
        }

        override fun onReset() {

        }

        override fun onLogUpdated(message: String) {
            Log.d(TAG, message)
        }

    }
}