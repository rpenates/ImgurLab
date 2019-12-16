package com.rpenates.imgurtestlab.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rpenates.imgurtestlab.data.repository.PhotoRepository

class CartActivityViewModelFactory (
    private val photoRepository: PhotoRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartActivityViewModel(photoRepository) as T
    }
}