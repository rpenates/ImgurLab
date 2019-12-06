package com.rpenates.imgurtestlab.core

import com.rpenates.imgurtestlab.BuildConfig

object AppConfig {

    const val DATABASE_NAME = "imgur-testlab-db"

        val baseHeaders = mapOf(
        "Authorization" to "Client-ID ${BuildConfig.imgur_client_id}"
    )
}