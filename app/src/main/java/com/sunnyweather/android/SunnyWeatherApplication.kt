package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN = L_TOKEN
        const val LOCAL_BASE_URL = L_LOCAL_BASE_URL
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}