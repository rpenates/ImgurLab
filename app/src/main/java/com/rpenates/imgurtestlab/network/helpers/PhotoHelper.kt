package com.rpenates.imgurtestlab.network.helpers

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.rpenates.imgurtestlab.core.DataTypes
import com.rpenates.imgurtestlab.data.models.Photo
import org.json.JSONArray
import org.json.JSONObject

object PhotoHelper {
    object PhotoDeserializer: ResponseDeserializable<List<Photo>> {
        override fun deserialize(response: String): List<Photo> {
            val output = JSONObject(response)
            val jsonOfPhotos = output.getJSONArray("data")
            val photos = ArrayList<Photo>()

            for (i in 0 until jsonOfPhotos.length()) {
                val singleJsonObject = jsonOfPhotos.getJSONObject(i)
                val photo = decodePhoto(singleJsonObject)
                photos.add(photo)
            }

            return photos
        }
    }

    fun decodePhoto(jsonPhoto: JSONObject) : Photo {
        return Photo(
            parentAlbumId = "default",
            id = jsonPhoto.getString("id"),
            title = jsonPhoto.getString("title"),
            photoUrl = getFirstImage(jsonPhoto.getJSONArray("images"))
        )
    }

    private fun getFirstImage(jsonOfImages: JSONArray): String {
        val firstElement = jsonOfImages[0] as JSONObject
        return if (firstElement.getString("type") == DataTypes.IMAGE) {
            firstElement.getString("link")
        } else {
            ""
        }
    }
}