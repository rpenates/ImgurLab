package com.rpenates.imgurtestlab.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.data.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartActivityViewModel(val photoRepository: PhotoRepository): ViewModel() {
    val LCAT = this.javaClass.simpleName
    val photoLiveData = MutableLiveData<List<Photo>>()

    fun showCartPhotos() {
        viewModelScope.launch (Dispatchers.Main) {
            val results: List<Photo> = async (Dispatchers.IO) {
                return@async photoRepository.getAllPhotos()
            }.await()
            photoLiveData.value = results
        }
    }

    fun deletefromCart(photo: Photo) {
        viewModelScope.launch {
            withContext(IO) {
                photoRepository.deletePhoto(photo)
            }
        }
    }
}