package com.rpenates.imgurtestlab

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager

class ImgurTestlabApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = BuildConfig.api_url
    }
}