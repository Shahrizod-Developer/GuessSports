package com.example.guesssports.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guesssports.R
import com.example.guesssports.databinding.FragmentCategoriesBinding
import com.example.guesssports.databinding.FragmentHomeBinding

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)



        binding.rectangle.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}