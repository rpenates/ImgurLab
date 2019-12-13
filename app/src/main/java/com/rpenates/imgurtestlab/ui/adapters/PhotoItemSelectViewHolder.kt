package com.rpenates.imgurtestlab.ui.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.data.models.Photo

class PhotoItemSelectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imgurThumbnail: ImageView = itemView.findViewById(R.id.small_card_photo)
    private val photoCheckBox: CheckBox = itemView.findViewById(R.id.photo_check_box)

    fun renderWithPhoto(photo: Photo) {
        if (photo.photoUrl.isEmpty()){
            imgurThumbnail.setImageDrawable(itemView.resources.getDrawable(R.mipmap.ic_launcher))
        } else {
            Glide.with(itemView)
                .load(photo.photoUrl)
                .centerCrop()
                .into(imgurThumbnail)
        }
        photoCheckBox.isActivated = photo.isSelected
    }
}