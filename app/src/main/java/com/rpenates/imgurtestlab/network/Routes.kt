package com.rpenates.imgurtestlab.network

import com.rpenates.imgurtestlab.BuildConfig

object Routes {

    val imageRoute = "/image"

    val galleryRoute = "/gallery"

    val imgurHeaders = mapOf(
        "Authorization" to "Client-ID ${BuildConfig.imgur_client_id}"
    )
}