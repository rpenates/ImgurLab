package com.rpenates.imgurtestlab.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.data.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
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
}