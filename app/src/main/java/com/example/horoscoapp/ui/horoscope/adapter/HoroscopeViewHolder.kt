package com.example.horoscoapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscoapp.databinding.ItemHoroscopeBinding
import com.example.horoscoapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscope: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.ivHoroscope.setImageResource(horoscope.img)
        binding.tvHoroscope.text = binding.tvHoroscope.context.getString(horoscope.name)
        binding.horoscope.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, onItemSelected = { onItemSelected(horoscope) })
        }
    }

    private fun startRotationAnimation(view: View, onItemSelected: () -> Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction {
                onItemSelected()
            }
            start()
        }
    }
}