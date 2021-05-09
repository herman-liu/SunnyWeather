package com.sunnyweather.android

import com.google.gson.Gson

inline fun <reified T> fromJson(json: String?): T =
    Gson().fromJson(json, T::class.java)