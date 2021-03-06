package com.github.libregallery.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.libregallery.activities.PicturePagerActivity
import com.github.libregallery.R
import com.github.libregallery.model.Picture

class ImageAdapter(private var context: Context?, private var picturesList: ArrayList<Picture>)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>()
{

    inner class ImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        var image : ImageView?=null

        init
        {
            image=itemView.findViewById(R.id.row_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.timeline_view_image_item,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int)
    {
        val currentPicture = picturesList[position]

        Glide.with(context!!)
            .load(currentPicture.getPath())
            .apply(RequestOptions().centerCrop())
            .into(holder.image!!)

        holder.image?.setOnClickListener{

            val intent = Intent(context, PicturePagerActivity::class.java)
            intent.putExtra("path",currentPicture.getPath())
            intent.putExtra("index",position)



            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }

    }

    override fun getItemCount(): Int
    {
        return picturesList.size
    }

}