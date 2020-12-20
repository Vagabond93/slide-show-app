package com.emonized.birthday.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.emonized.birthday.R
import com.emonized.birthday.databinding.ActivityDashboardBinding
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(){

    lateinit var viewBinding : ActivityDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)


        memoirs_container_fl.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
    }
}