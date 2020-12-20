package com.emonized.birthday.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emonized.birthday.R
import com.emonized.birthday.constant.AppConstant
import com.emonized.birthday.databinding.ViewItemGalleryBinding
import com.squareup.picasso.Picasso

class GalleryAdapter(private val context: Context, var imageIds: MutableList<Int>) :
    RecyclerView.Adapter<GalleryAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            DataBindingUtil.inflate<ViewItemGalleryBinding>(
                LayoutInflater.from(
                    context
                ), R.layout.view_item_gallery, parent, false
            ).root
        )
    }

    override fun getItemCount(): Int {
        return imageIds.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageId = imageIds[position]

        if (imageId == AppConstant.EMPTY_IMAGE_ID) {
            holder.cardView.visibility = View.INVISIBLE
        } else {
            holder.cardView.visibility = View.VISIBLE
            Picasso.get()
                .load(imageId)
                .resize(600,600)
                .into(holder.imageView)
        }
    }

    public fun getItemSize() : Int{
        return imageIds.size
    }

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: AppCompatImageView = view.findViewById(R.id.gallery_item_iv)
        var cardView : CardView = view.findViewById(R.id.gallery_item_container)


    }
}