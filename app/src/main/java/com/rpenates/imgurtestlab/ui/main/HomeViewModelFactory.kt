package com.rpenates.imgurtestlab.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rpenates.imgurtestlab.data.repository.PhotoRepository

class HomeViewModelFactory (
    private val photoRepository: PhotoRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(photoRepository) as T
    }
}