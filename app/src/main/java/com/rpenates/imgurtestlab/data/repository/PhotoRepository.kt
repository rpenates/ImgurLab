package com.rpenates.imgurtestlab.data.repository

import com.rpenates.imgurtestlab.data.dao.PhotoDao
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.network.api.ImgurApi

class PhotoRepository private  constructor(
    private val photoDao: PhotoDao,
    private val imgurApi: ImgurApi
) {

    fun getPhoto(photoId: String) = photoDao.getPhoto(photoId)

    fun getPhotosByAlbum(albumId: String) = photoDao.getPhotosByAlbum(albumId)

    fun getAllPhotos() = photoDao.getAllPhotos()

    suspend fun searchPhotos(searchQuery: String): List<Photo> = imgurApi.queryPhotos(searchQuery)

    fun savePhoto(photo: Photo) = photoDao.savePhoto(photo)

    fun deletePhoto(photo: Photo) = photoDao.deletePhoto(photo)


    companion object {
        @Volatile private var instance: PhotoRepository? = null

        fun getInstance(photoDao: PhotoDao, imgurApi: ImgurApi) =
            instance ?: synchronized(this) {
                instance ?: PhotoRepository(photoDao, imgurApi).also { instance = it }
            }
    }

}