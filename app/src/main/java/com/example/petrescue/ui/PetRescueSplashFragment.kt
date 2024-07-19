package com.example.petrescue.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.petrescue.R
import com.example.petrescue.databinding.FragmentPetRescueSplashBinding


class PetRescueSplashFragment : Fragment() {

    private var _binding: FragmentPetRescueSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentPetRescueSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners(){
        with(binding){
            splashButtonStart.setOnClickListener{
                findNavController().navigate(PetRescueSplashFragmentDirections.actionPetRescueSplashFragmentToPetRescueHomeFragment())
            }
        }
    }
}