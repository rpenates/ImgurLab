package com.rpenates.imgurtestlab.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.data.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val photoRepository: PhotoRepository): ViewModel() {

    val LCAT = this.javaClass.simpleName
    val photosLiveData = MutableLiveData<List<Photo>>()

    fun searchPhotos(stringQuery: String) {
        viewModelScope.launch {
            withContext(Main) {
                val searchResults = photoRepository.searchPhotos(stringQuery)
                photosLiveData.value = searchResults
            }
        }
    }

    fun addToCart(cartItems: ArrayList<Photo>): Boolean {
        for (item in cartItems) {
            if (item.isSelected){
                viewModelScope.launch {
                    withContext(IO) {
                        photoRepository.savePhoto(item)
                        println("Photo ${item.id} has been saved")
                    }
                }

            }
        }
        return cartItems.isNotEmpty()
    }
}