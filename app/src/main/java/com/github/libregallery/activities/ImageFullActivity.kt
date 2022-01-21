package com.github.libregallery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.github.libregallery.R

class ImageFullActivity : AppCompatActivity()
{
    private var imagePath :String ?=null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full)

        initializeValues()

        setImage()
    }


    private fun initializeValues()
    {
        imagePath=intent.getStringExtra("path")
    }

    private fun setImage()
    {
        Glide.with(this)
            .load(imagePath)
            .into(findViewById(R.id.image_view))

    }
}