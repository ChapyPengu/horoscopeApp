package com.example.horoscoapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscoapp.databinding.FragmentHoroscopeBinding
import com.example.horoscoapp.domain.model.HoroscopeDetailModel
import com.example.horoscoapp.domain.model.HoroscopeInfo
import com.example.horoscoapp.domain.model.HoroscopeInfo.*
import com.example.horoscoapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HoroscopeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        adapter = HoroscopeAdapter(onItemSelected = {
            val symbol = when (it) {
                Aquarius -> HoroscopeDetailModel.Aquarius
                Aries -> HoroscopeDetailModel.Aries
                Cancer -> HoroscopeDetailModel.Cancer
                Capricorn -> HoroscopeDetailModel.Capricorn
                Gemini -> HoroscopeDetailModel.Gemini
                Leo -> HoroscopeDetailModel.Leo
                Libra -> HoroscopeDetailModel.Libra
                Pisces -> HoroscopeDetailModel.Pisces
                Sagittarius -> HoroscopeDetailModel.Sagittarius
                Scorpio -> HoroscopeDetailModel.Scorpio
                Taurus -> HoroscopeDetailModel.Taurus
                Virgo -> HoroscopeDetailModel.Virgo
            }

            findNavController().navigate(
//                Toast.makeText(context, "", Toast.LENGTH_LONG).show()
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(symbol)
            )
        })
        binding.rvHoroscope.layoutManager = GridLayoutManager(context, 2)
        binding.rvHoroscope.adapter = adapter
    }
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    adapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun onItemSelected() {

    }
}