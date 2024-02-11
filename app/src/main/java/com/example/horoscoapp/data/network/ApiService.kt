package com.example.horoscoapp.data.network

import com.example.horoscoapp.data.network.response.HoroscopeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): HoroscopeResponse


}