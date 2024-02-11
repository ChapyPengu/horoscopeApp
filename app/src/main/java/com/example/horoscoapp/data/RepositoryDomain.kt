package com.example.horoscoapp.data

import android.util.Log
import com.example.horoscoapp.data.network.ApiService
import com.example.horoscoapp.data.network.response.HoroscopeResponse
import com.example.horoscoapp.domain.Repository
import com.example.horoscoapp.domain.model.HoroscopeModel
import javax.inject.Inject

class RepositoryDomain @Inject constructor(private val apiService: ApiService): Repository {
    override suspend fun getPrediction(sign: String): HoroscopeModel? {
        runCatching {
            apiService.getHoroscope(sign)
        }.onSuccess {
            Log.i("message", "Success")
            return it.toDomain()
        }.onFailure {
            Log.i("message", "Error")
        }
        return null
    }
}