package com.rpenates.imgurtestlab.data.dao

import androidx.room.*
import com.rpenates.imgurtestlab.data.models.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos WHERE parentAlbumId = :albumId")
    fun getPhotosByAlbum(albumId: String): List<Photo>

    @Query("SELECT * FROM photos WHERE id = :photoId")
    fun getPhoto(photoId: String): Photo

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhoto(photo: Photo)

    @Delete
    fun deletePhoto(photo: Photo)
}