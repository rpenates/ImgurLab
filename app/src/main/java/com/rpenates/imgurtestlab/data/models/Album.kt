package com.rpenates.imgurtestlab.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album (
    @PrimaryKey
    val id: String,
    val userId: String,
    val albumName: String
)