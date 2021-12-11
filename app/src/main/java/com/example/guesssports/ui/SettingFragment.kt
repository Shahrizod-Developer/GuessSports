package com.example.guesssports.ui

import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.guesssports.MainActivity
import com.example.guesssports.R
import com.example.guesssports.Settings
import com.example.guesssports.Sound
import com.example.guesssports.databinding.DialogLanguageBinding
import com.example.guesssports.databinding.FragmentHomeBinding
import com.example.guesssports.databinding.FragmentSettingBinding
import java.util.*
import android.preference.PreferenceManager

import android.content.SharedPreferences

import androidx.core.app.ActivityCompat.recreate

import androidx.appcompat.app.AppCompatActivity
import android.app.Application
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.Gravity


class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.soundOn.isChecked = Sound.mediaPlayer.isPlaying
        binding.vibrationOn.isChecked=Settings.vibrate
        binding.rectangle.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.soundOn.setOnCheckedChangeListener { compoundButton, ischecked ->

            if(ischecked)
            {
                Sound.mediaPlayer.start()
            }
            else{
                Sound.mediaPlayer.pause()
            }
        }

        binding.vibrationOn.setOnCheckedChangeListener { compoundButton, b ->
            Settings.vibrate = b
        }

        binding.lang.setOnClickListener {

                val alertDialog = AlertDialog.Builder(requireContext()).create()
                val inflater = layoutInflater
                val bn = DialogLanguageBinding.inflate(inflater)
                alertDialog.setView(bn.root)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)
                bn.apply {
                    english.setOnClickListener {

                        var language = "eng"
                        setLocale(language)
                        binding.til.setImageResource(R.drawable.english)
                        alertDialog.cancel()

                    }
                    russian.setOnClickListener {
                        var language = "ru"
                        setLocale(language)
                        binding.til.setImageResource(R.drawable.russian)
                        alertDialog.cancel()
                    }
                    china.setOnClickListener {
                        var language = "zh"
                        setLocale(language)
                        binding.til.setImageResource(R.drawable.china)
                        alertDialog.cancel()
                    }

                }
                alertDialog.show()
        }
        (requireContext() as MainActivity).backPress = {
            findNavController().navigate(R.id.homeFragment)
        }

        return binding.root
    }

    private fun setLocale(language: String) {

        var resources: Resources = resources
        var metric: DisplayMetrics = resources.displayMetrics
        val configuration = resources.configuration
        configuration.locale = Locale(language)
        resources.updateConfiguration(configuration, metric)
        onConfigurationChanged(configuration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        binding.sound.text = requireContext().resources.getString(R.string.sound)
        binding.vibration.text = requireContext().resources.getString(R.string.vibration)
        binding.language.text = requireContext().resources.getString(R.string.language)

    }
}