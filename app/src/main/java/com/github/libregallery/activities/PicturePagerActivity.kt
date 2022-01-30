package com.github.libregallery.activities

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.github.libregallery.R
import com.github.libregallery.adapters.PicturePagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PicturePagerActivity : AppCompatActivity()
{
    private var imagePath :String ?=null
    private var imageIndex :  Int ?= null
    private var adapter : PicturePagerAdapter ?= null
    private var viewPager : ViewPager2 ?= null

    private var menuButton : FloatingActionButton ?= null
    private var shareButton : FloatingActionButton ?= null

    private var clicked : Boolean = false

    private var fromBottom : Animation ?= null
    private var toBottom : Animation ?= null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_pager)

        initializeVariables()

        setViewPager()

        setListeners()
    }




    private fun initializeVariables()
    {
        imagePath=intent.getStringExtra("path")
        imageIndex=intent.getIntExtra("index",-1)
        adapter = PicturePagerAdapter(supportFragmentManager,lifecycle)
        viewPager = findViewById(R.id.view_pager)

        menuButton = findViewById(R.id.floating_menu_button)
        shareButton = findViewById(R.id.floating_share_button)

        fromBottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom_animation)
        toBottom = AnimationUtils.loadAnimation(this,R.anim.to_bottom_animation)

    }

    private fun setViewPager()
    {
        viewPager?.adapter = adapter
        viewPager?.setCurrentItem(imageIndex!!,false)
    }

    private fun setListeners()
    {
        menuButton?.setOnClickListener{onMenuButtonClicked()}
    }

    private fun onMenuButtonClicked()
    {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked : Boolean)
    {
        if(!clicked)
        {
            shareButton?.visibility = View.VISIBLE
        }
        else
        {
            shareButton?.visibility = View.GONE
        }
    }

    private fun setAnimation(clicked : Boolean)
    {
        if(!clicked)
        {
            shareButton?.startAnimation(fromBottom)

        }
        else
        {
            shareButton?.startAnimation(toBottom)
        }
    }
}