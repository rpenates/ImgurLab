package com.rpenates.imgurtestlab.core

import android.content.Context
import com.rpenates.imgurtestlab.data.AppDatabase
import com.rpenates.imgurtestlab.data.repository.PhotoRepository
import com.rpenates.imgurtestlab.network.api.ImgurApi
import com.rpenates.imgurtestlab.ui.home.HomeViewModelFactory

object DI {
    private fun getPhotorepository(context: Context): PhotoRepository {
        return PhotoRepository.getInstance(
            AppDatabase.getInstance(context).photoDao(),
            ImgurApi.getInstance()
        )
    }

    fun provideHomeViewModelFactory(context: Context): HomeViewModelFactory {
        val repository = getPhotorepository(context)
        return HomeViewModelFactory(repository)
    }

    // TODO: implement album dependency injection
}