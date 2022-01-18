package com.github.libregallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.libregallery.fragments.AlbumsFragment
import com.github.libregallery.fragments.TimelineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()
{
    private val timelineFragment = TimelineFragment()
    private val albumsFragment = AlbumsFragment()

    private val navigationBar: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation_bar)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        replaceFragment(timelineFragment)

        setListeners()
    }

    private fun replaceFragment(fragment : Fragment)
    {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
    }

    private fun setListeners()
    {
        navigationBar.setOnItemSelectedListener { item ->
            when(item.itemId)
            {
                R.id.timeline_item -> replaceFragment(timelineFragment)
                R.id.albums_item -> replaceFragment(albumsFragment)
            }
            true
        }
    }
}