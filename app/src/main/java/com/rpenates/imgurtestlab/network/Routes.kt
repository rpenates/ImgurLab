package com.rpenates.imgurtestlab.network

import com.rpenates.imgurtestlab.BuildConfig

object Routes {

    val imageRoute = "/image"

    val imgurHeaders = mapOf(
        "Authorization" to "CLIENT_ID ${BuildConfig.imgur_client_id}"
    )
}