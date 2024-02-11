package com.example.horoscoapp.domain

import com.example.horoscoapp.data.network.response.HoroscopeResponse
import com.example.horoscoapp.domain.model.HoroscopeModel

interface Repository {

    suspend fun getPrediction(sign: String): HoroscopeModel?
}