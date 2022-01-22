package com.github.libregallery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.libregallery.R

class PictureFragment (picturePath : String): Fragment()
{
    private var picturePath : String ?= null

    private var imageView : ImageView ?= null


    init
    {
        this.picturePath=picturePath
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.picture_fragment, container, false)

        imageView = view.findViewById(R.id.image_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setPicture()

    }

    private fun setPicture()
    {
        Glide.with(this)
                .load(picturePath)
                .into(imageView!!)

    }
}