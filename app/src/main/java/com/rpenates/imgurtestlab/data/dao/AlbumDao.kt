package com.rpenates.imgurtestlab.data.dao

import androidx.room.*
import com.rpenates.imgurtestlab.data.models.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getSingleAlbum(albumId: String): Album

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbum(album: Album)

    @Delete
    fun deleteAlbum(album:Album)
}