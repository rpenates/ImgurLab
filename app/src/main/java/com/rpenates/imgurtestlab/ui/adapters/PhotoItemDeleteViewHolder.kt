package com.rpenates.imgurtestlab.ui.adapters

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.data.models.Photo

class PhotoItemDeleteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imgurThumbnail: ImageView = itemView.findViewById(R.id.card_photo_thumbnail)
    val photoDelButton: ImageButton = itemView.findViewById(R.id.photo_delete_button)

    fun renderWithPhoto(photo: Photo) {
        if (photo.photoUrl.isEmpty()){
            imgurThumbnail.setImageDrawable(itemView.resources.getDrawable(R.mipmap.ic_launcher))
        } else {
            Glide.with(itemView)
                .load(photo.photoUrl)
                .centerCrop()
                .into(imgurThumbnail)
        }
    }
}