package com.example.horoscoapp.domain.model

sealed class HoroscopeDetailState {
    data object Loading: HoroscopeDetailState()
    data class Error(val error: String): HoroscopeDetailState()
    data class Success(val data: String, val sign: String, val horoscope: HoroscopeDetailModel): HoroscopeDetailState()

}