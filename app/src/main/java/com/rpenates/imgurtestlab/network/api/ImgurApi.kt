package com.rpenates.imgurtestlab.network.api

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.network.Routes
import com.rpenates.imgurtestlab.network.helpers.PhotoHelper

class ImgurApi {

    suspend fun queryPhotos(stringQuery: String): List<Photo> =
        Fuel.get("${Routes.imageRoute}/gallery/search/top/day/01?q=${stringQuery}&q_type=jpg")
            .header(Routes.imgurHeaders)
            .also { println(it) }
            .awaitObject(PhotoHelper.PhotoDeserializer)

    companion object {
        @Volatile
        private var instance: ImgurApi? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ImgurApi().also { instance = it }
            }
    }
}