package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {
    //place service
    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    //weather service
    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

    //default weather service
    private val defaultWeatherService = Retrofit.Builder()
        .baseUrl(SunnyWeatherApplication.LOCAL_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    suspend fun getDailyWeather() =
        defaultWeatherService.getDefaultDailyWeather().await()

    suspend fun getRealtimeWeather() =
        defaultWeatherService.getDefaultRealtimeWeather().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}