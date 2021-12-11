package com.example.guesssports.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guesssports.MainActivity
import com.example.guesssports.R
import com.example.guesssports.databinding.FragmentPlayBinding
import com.example.guesssports.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)

        val trueAns = arguments?.getString("trueAns")

        if(trueAns.toString() == "40")
        {
            binding.win.visibility = View.VISIBLE
            binding.over.visibility = View.GONE
            binding.natija.text = trueAns.toString()
        }
        else{
            binding.win.visibility = View.GONE
            binding.over.visibility = View.VISIBLE
            binding.natija.text = trueAns.toString()
        }
        (requireContext() as MainActivity).backPress = {
            findNavController().navigate(R.id.homeFragment)
        }
        binding.restart.setOnClickListener {
            findNavController().navigate(R.id.playFragment)
        }
        binding.mainMenu.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        binding.rectangle.setOnClickListener {
            findNavController().navigate(R.id.playFragment)
        }
        return binding.root
    }

}