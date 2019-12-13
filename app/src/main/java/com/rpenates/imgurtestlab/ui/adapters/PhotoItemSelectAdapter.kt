package com.rpenates.imgurtestlab.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.data.models.Photo

class PhotoItemSelectAdapter: RecyclerView.Adapter<PhotoItemSelectViewHolder>() {

    var photoList: ArrayList<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemSelectViewHolder {
        var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_select_layout, parent, false)
        return PhotoItemSelectViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoItemSelectViewHolder, position: Int) {
        var photo = photoList[position]
        holder?.renderWithPhoto(photo)
    }

}