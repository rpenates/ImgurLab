package com.rpenates.imgurtestlab.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "photos",
    foreignKeys = [ForeignKey(entity = Album::class,
        parentColumns = ["id"],
        childColumns = ["parentAlbumId"],
        onDelete = ForeignKey.CASCADE)])
data class Photo (
    @PrimaryKey val id: String,
    val parentAlbumId: String,
    val photoUrl: String,
    val title: String
) {
    override fun toString(): String {
        return "Photo(id='$id', parentAlbumId='$parentAlbumId', photoUrl='$photoUrl', title='$title')"
    }
}