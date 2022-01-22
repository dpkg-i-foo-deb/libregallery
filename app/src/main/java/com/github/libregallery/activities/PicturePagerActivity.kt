package com.github.libregallery.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.github.libregallery.R
import com.github.libregallery.adapters.PicturePagerAdapter

class PicturePagerActivity : AppCompatActivity()
{
    private var imagePath :String ?=null
    private var imageIndex :  Int ?= null
    private var adapter : PicturePagerAdapter ?= null
    private var viewPager : ViewPager2 ?= null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_pager)

        initializeValues()

        setViewPager()

    }


    private fun initializeValues()
    {
        imagePath=intent.getStringExtra("path")
        imageIndex=intent.getIntExtra("index",-1)
        adapter = PicturePagerAdapter(supportFragmentManager,lifecycle)
        viewPager = findViewById(R.id.view_pager)
    }

    private fun setViewPager()
    {
        viewPager?.adapter = adapter
        viewPager?.setCurrentItem(imageIndex!!,false)
    }

}