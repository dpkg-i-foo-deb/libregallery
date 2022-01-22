package com.github.libregallery.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.libregallery.fragments.PictureFragment
import com.github.libregallery.model.Gallery

class PicturePagerAdapter (fragmentManager : FragmentManager, lifecycle : Lifecycle) :
    FragmentStateAdapter (fragmentManager, lifecycle)
{
    private var gallery = Gallery
    private var picturePath : String = ""


    override fun getItemCount(): Int
    {
        return gallery.getPictureCount()!!
    }

    override fun createFragment(position: Int): Fragment
    {
        picturePath= gallery.getPicturePath(position)!!

        return PictureFragment(picturePath)
    }

}