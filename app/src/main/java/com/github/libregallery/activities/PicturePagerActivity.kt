package com.github.libregallery.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.viewpager2.widget.ViewPager2
import com.github.libregallery.R
import com.github.libregallery.adapters.PicturePagerAdapter
import com.github.libregallery.fragments.PictureFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

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
        shareButton?.setOnClickListener{onShareButtonClicked()}
    }

    private fun onShareButtonClicked()
    {
        val currentItem = supportFragmentManager.findFragmentByTag("f"+viewPager?.currentItem) as PictureFragment

        val picturePath = currentItem.getPath()!!

        val uri = FileProvider.getUriForFile(this,"com.github.libregallery.fileProvider",
            File(picturePath)
        )

        val share = Intent(Intent.ACTION_SEND)

        share.type = "image/*"

        share.putExtra(Intent.EXTRA_STREAM, uri)

        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val chooser: Intent? = Intent.createChooser(share,"SharePicture")

        chooser?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        applicationContext.startActivity(chooser)

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