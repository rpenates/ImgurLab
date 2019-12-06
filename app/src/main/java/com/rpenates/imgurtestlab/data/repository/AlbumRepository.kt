package com.rpenates.imgurtestlab.data.repository

import com.rpenates.imgurtestlab.data.dao.AlbumDao
import com.rpenates.imgurtestlab.data.dao.PhotoDao
import com.rpenates.imgurtestlab.data.models.Album

class AlbumRepository private constructor(
    private val albumDao: AlbumDao,
    private val photoDao: PhotoDao
) {

    fun getSingleAlbum(albumId: String) = albumDao.getSingleAlbum(albumId)

    fun saveAlbum(album: Album) = albumDao.saveAlbum(album)

    fun deleteAlbum(album: Album): Boolean {
        val currentPhotoList = photoDao.getPhotosByAlbum(album.id)
        return if (currentPhotoList.isEmpty()){
            albumDao.deleteAlbum(album)
            true
        } else {
            println("The album cannot be deleted, is not empty")
            false
        }
    }

    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(albumDao: AlbumDao, photoDao: PhotoDao) =
            instance?: synchronized(this) {
                instance?: AlbumRepository(albumDao, photoDao).also {instance = it}
            }
    }
}