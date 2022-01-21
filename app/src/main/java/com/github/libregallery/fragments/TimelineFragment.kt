package com.github.libregallery.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.libregallery.R
import com.github.libregallery.model.Gallery
import com.github.libregallery.model.Picture
import com.github.libregallery.adapters.ImageAdapter

class TimelineFragment : Fragment()
{

    private var imageRecycler : RecyclerView?=null

    private var allPictures : ArrayList<Picture> ?=null

    private var gallery = Gallery


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.timeline_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        initializeVariables()

        getPictures()

        setImageRecycler()

    }

    private fun initializeVariables()
    {
        imageRecycler= view?.findViewById(R.id.image_recycler)

        allPictures=ArrayList()

    }

    private fun getPictures()
    {
        if(allPictures!!.isEmpty())
        {
            allPictures = gallery.getAllImages(this.context)
        }

        imageRecycler?.adapter = ImageAdapter(activity?.applicationContext,allPictures!!)
    }

    private fun setImageRecycler()
    {
        imageRecycler?.layoutManager=GridLayoutManager(activity,4)
        imageRecycler?.setHasFixedSize(true)
    }
}