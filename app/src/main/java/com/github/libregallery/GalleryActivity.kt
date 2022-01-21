package com.github.libregallery

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.libregallery.fragments.AlbumsFragment
import com.github.libregallery.fragments.TimelineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class GalleryActivity : AppCompatActivity()
{
    private val timelineFragment = TimelineFragment()
    private val albumsFragment = AlbumsFragment()

    private var navigationBar: BottomNavigationView?=null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        replaceFragment(timelineFragment)

        initializeValues()

        requestPermissions()

        setListeners()
    }

    private fun replaceFragment(fragment : Fragment)
    {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
    }

    private fun initializeValues()
    {
        navigationBar= findViewById(R.id.bottom_navigation_bar)
    }

    private fun requestPermissions()
    {
        if(ContextCompat.checkSelfPermission
                (this@GalleryActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this@GalleryActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),101)
        }

    }

    private fun setListeners()
    {
        navigationBar?.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.timeline_item -> replaceFragment(timelineFragment)
                R.id.albums_item -> replaceFragment(albumsFragment)
            }
            true
        }
    }


}