package com.github.libregallery.model

class Picture(private var imagePath: String?) {

    fun getPath () : String?
    {
        return imagePath
    }
}