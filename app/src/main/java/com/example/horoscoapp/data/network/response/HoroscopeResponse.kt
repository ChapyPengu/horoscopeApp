package com.example.horoscoapp.data.network.response

import com.example.horoscoapp.domain.model.HoroscopeModel
import com.google.gson.annotations.SerializedName

data class HoroscopeResponse (
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
) {

    fun toDomain(): HoroscopeModel {
        return HoroscopeModel(
            horoscope = horoscope,
            sing = sign
        )
    }
}