package com.example.horoscoapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscoapp.domain.model.HoroscopeDetailModel
import com.example.horoscoapp.domain.model.HoroscopeDetailModel.*
import com.example.horoscoapp.domain.model.HoroscopeDetailState
import com.example.horoscoapp.domain.model.HoroscopeInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailBinding: HoroscopeDetailViewModel by viewModels()
    private val args by navArgs<HoroscopeDetailActivityArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailBinding.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailBinding.state.collect {
                    when (it) {
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun successState(horoscopeDetailState: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvHoroscope.text = horoscopeDetailState.sign
        binding.tvDetails.text = horoscopeDetailState.data

        val img = when (horoscopeDetailState.horoscope) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }

        binding.ivHoroscope.setImageResource(img)
    }
}