package com.rpenates.imgurtestlab.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo (
    @PrimaryKey val id: String,
    val photoUrl: String,
    val title: String,
    val upvotes: Int,
    val downvotes: Int,
    val views: Int
) {
    var isSelected: Boolean = false
    override fun toString(): String {
        return "Photo(id='$id', photoUrl='$photoUrl', title='$title')"
    }
}