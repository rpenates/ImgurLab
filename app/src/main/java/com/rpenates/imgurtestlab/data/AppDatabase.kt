package com.rpenates.imgurtestlab.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rpenates.imgurtestlab.core.AppConfig.DATABASE_NAME
import com.rpenates.imgurtestlab.data.dao.AlbumDao
import com.rpenates.imgurtestlab.data.dao.PhotoDao
import com.rpenates.imgurtestlab.data.models.Album
import com.rpenates.imgurtestlab.data.models.Photo

@Database(
    entities = [
        Album::class,
        Photo::class
    ],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // TODO: buildDatabase function
        private fun buildDatabase(context: Context) : AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}