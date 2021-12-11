package com.example.guesssports.ui

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guesssports.R
import com.example.guesssports.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }
        binding.categories.setOnClickListener {
            findNavController().navigate(R.id.categoriesFragment)

        }

        binding.start.setOnClickListener {
            findNavController().navigate(R.id.playFragment)


        }
        return binding.root
    }

}