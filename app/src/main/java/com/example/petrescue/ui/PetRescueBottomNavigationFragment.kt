package com.example.petrescue.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.example.petrescue.R
import com.example.petrescue.databinding.FragmentPetRescueBottomNavigationBinding
import com.example.petrescue.databinding.FragmentPetRescueHomeBinding


class PetRescueBottomNavigationFragment : Fragment() {

    private var _binding: FragmentPetRescueBottomNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetRescueBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListener()
    }

    private fun setupView(){
        replaceFragment(PetRescueHomeFragment())
    }

    private fun setupListener(){
        with(binding){
            petRescueBottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home -> replaceFragment(PetRescueHomeFragment())
                    R.id.favorites -> replaceFragment(PetRescueFavoritesFragment())
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.pet_rescue_bottom_navigation_container,fragment)
        fragmentTransaction.commit()
    }

}