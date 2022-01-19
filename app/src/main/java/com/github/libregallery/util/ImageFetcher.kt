package com.github.libregallery.util

import android.content.Context
import android.provider.MediaStore
import com.github.libregallery.model.Picture
import kotlin.Exception

class ImageFetcher {
    private var allImages: ArrayList<Picture>? = null

    init
    {
        allImages = ArrayList()
    }

    fun getAllImages(context: Context?) : ArrayList<Picture>?
    {
        val allUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        val cursor = context?.contentResolver?.query(allUri,projection,null,null, null)

        var path : String

        var picture : Picture

        try
        {
            cursor!!.moveToFirst()

            do
            {
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))

                picture= Picture(path)

                allImages?.add(picture)

            } while (cursor.moveToNext())

            cursor.close()

        } catch(e:Exception)
        {
            e.printStackTrace()
        }

        return allImages
    }
}