@file:Suppress("unused")

package com.github.libregallery

import android.app.Application
import com.google.android.material.color.DynamicColors

class GalleryApp : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        // Apply dynamic colour
        DynamicColors.applyToActivitiesIfAvailable(this)

    }

}