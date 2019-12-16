package com.rpenates.imgurtestlab.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.ui.cart.CartActivityViewModel

class PhotoItemDeleteIAdapter: RecyclerView.Adapter<PhotoItemDeleteViewHolder>() {

    var photoList: ArrayList<Photo> = ArrayList()
    lateinit var activityViewModel: CartActivityViewModel

    fun setViewModel(viewModel: CartActivityViewModel) {
        activityViewModel = viewModel
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemDeleteViewHolder {
        var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_delete_layout, parent, false)
        return PhotoItemDeleteViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoItemDeleteViewHolder, position: Int) {
        var photo = photoList[position]
        holder.renderWithPhoto(photo)

        holder.photoDelButton.setOnClickListener {
            activityViewModel.deletefromCart(photo)
            photoList.remove(photo)
            notifyDataSetChanged()
        }
    }


}