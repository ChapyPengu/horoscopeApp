package com.example.horoscoapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscoapp.domain.model.HoroscopeDetailModel
import com.example.horoscoapp.domain.model.HoroscopeDetailState
import com.example.horoscoapp.domain.model.HoroscopeModel
import com.example.horoscoapp.domain.usecase.GetHoroscopeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase): ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope: HoroscopeDetailModel

    fun getHoroscope(sign: HoroscopeDetailModel) {
        horoscope = sign
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            val result = withContext(Dispatchers.IO) {
                getHoroscopeUseCase(sign.name)
            }
            if (result != null) {
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sing, horoscope)
            } else {
                _state.value = HoroscopeDetailState.Error("Error xd")
            }
        }
    }
}