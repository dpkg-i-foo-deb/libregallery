package com.github.libregallery.model

import android.content.Context
import android.provider.MediaStore

object Gallery
{
    private var pictures : ArrayList<Picture> ?= null

    private var sortByDateDesc : String ?= null

    init
    {
        initializeVariables()
        createSortOrders()
    }

    private fun initializeVariables()
    {
        pictures = ArrayList()
    }

    private fun createSortOrders()
    {
        sortByDateDesc = MediaStore.Images.Media.DATE_ADDED + " DESC"
    }

    fun getAllImages(context: Context?) : ArrayList<Picture>?
    {
        val allUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        val cursor = context?.contentResolver?.query(allUri,projection,null,null, sortByDateDesc)

        var path : String

        var picture : Picture

        try
        {
            cursor!!.moveToFirst()

            do
            {
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))

                picture= Picture(path)

                pictures?.add(picture)

            } while (cursor.moveToNext())

            cursor.close()

        } catch(e:Exception)
        {
            e.printStackTrace()
        }

        return pictures
    }
}