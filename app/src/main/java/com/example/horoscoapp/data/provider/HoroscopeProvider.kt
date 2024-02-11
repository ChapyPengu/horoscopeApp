package com.example.horoscoapp.data.provider

import com.example.horoscoapp.domain.model.HoroscopeInfo
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {

    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            HoroscopeInfo.Aries, HoroscopeInfo.Taurus, HoroscopeInfo.Gemini,
            HoroscopeInfo.Aquarius, HoroscopeInfo.Taurus, HoroscopeInfo.Cancer,
            HoroscopeInfo.Leo, HoroscopeInfo.Scorpio, HoroscopeInfo.Capricorn,
            HoroscopeInfo.Libra, HoroscopeInfo.Sagittarius, HoroscopeInfo.Pisces
        )
    }
}