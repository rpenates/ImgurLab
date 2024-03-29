package com.rpenates.imgurtestlab.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.data.models.Photo
import kotlinx.android.synthetic.main.photo_item_layout.view.*

class PhotoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imgurImage: ImageView = itemView.findViewById(R.id.imgur_photo)
    private val imgurTitle: TextView = itemView.findViewById(R.id.imgur_title)
    private val imgurViews: TextView = itemView.findViewById(R.id.text_views)
    private val imgurUpvotes: TextView = itemView.findViewById(R.id.text_upvote)
    private val imgurDownvotes: TextView = itemView.findViewById(R.id.text_downvote)

    fun renderWithPhoto(photo: Photo) {
        imgurTitle.text = photo.title
        if (photo.photoUrl.isEmpty()){
            imgurImage.setImageDrawable(itemView.resources.getDrawable(R.mipmap.ic_launcher))
        } else {
            Glide.with(itemView)
                .load(photo.photoUrl)
                .centerCrop()
                .into(imgurImage)
        }
        imgurViews.text = photo.views.toString()
        imgurUpvotes.text = photo.upvotes.toString()
        imgurDownvotes.text = photo.downvotes.toString()
    }
}