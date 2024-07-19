package com.example.petrescue.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petrescue.R
import com.example.petrescue.adapters.PetRescueFavoritesAdapter
import com.example.petrescue.adapters.PetRescueHomeAdapter
import com.example.petrescue.databinding.FragmentPetRescueFavoritesBinding
import com.example.petrescue.databinding.FragmentPetRescueHomeBinding
import com.example.petrescue.viewModels.PetRescueViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PetRescueFavoritesFragment : Fragment() {

    private var _binding: FragmentPetRescueFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PetRescueViewModel by activityViewModel()
    private val adapter by lazy { PetRescueFavoritesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetRescueFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView(){
        with(binding){
            petRescueFavoritesRecycler.adapter = adapter
            adapter.submitData(viewModel.images, viewModel.breed)
        }
    }

}