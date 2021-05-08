package com.sunnyweather.android

import android.content.Context
import android.widget.Toast

fun String.makeToast(context: Context = SunnyWeatherApplication.context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}

fun Int.makeToast(context: Context = SunnyWeatherApplication.context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}