package com.example.petrescue.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.petrescue.R
import com.example.petrescue.adapters.PetRescueHomeAdapter
import com.example.petrescue.databinding.FragmentPetRescueHomeBinding
import com.example.petrescue.databinding.PetRescueBottomSheetBinding
import com.example.petrescue.utils.Resource
import com.example.petrescue.viewModels.PetRescueViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PetRescueHomeFragment : Fragment() {

    private var _binding: FragmentPetRescueHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PetRescueViewModel by viewModel()
    private val adapter by lazy { PetRescueHomeAdapter() }
    private val dataShimmer = listOf("", "", "", "", "", "", "", "")
    private var breed: String = "akita"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetRescueHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGetBreeds()
        viewModel.onGetBreedsImage(breed)

        setupView()
        setupObservables()
        setupListeners()
    }

    private fun setupView() {
        with(binding) {

            petRescueAnimalRecycler.adapter = adapter
            adapter.submitData(dataShimmer, "", true)

            petRescueHomeSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val searchText = query?.lowercase()
                    breed = searchText.toString()
                    searchText?.let { animalBreed -> viewModel.onGetBreedsImage(animalBreed) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            });
        }
    }

    private fun setupListeners() {
        with(binding) {
            petRescueHomeAddress.setOnClickListener {
                showDialog()
            }
        }
    }

    private fun setupObservables() {
        with(viewModel) {
            name.observe(viewLifecycleOwner) {
                binding.petRescueHomeAddress.text = it
            }
            sprecificBreed.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.petRescueAnimalRecycler.isVisible = true
                        binding.petRescueAnimalEmpty.isVisible = false
                        adapter.submitData(dataShimmer, "", true)
                    }

                    is Resource.Succeded -> {
                        binding.petRescueAnimalRecycler.isVisible = true
                        binding.petRescueAnimalEmpty.isVisible = false
                        adapter.submitData(it.data.message, breed, false)
                    }

                    else -> {
                        binding.petRescueAnimalRecycler.isVisible = false
                        binding.petRescueAnimalEmpty.isVisible = true
                    }
                }
            }
        }
    }

    private fun showDialog() {
        var dialog = Dialog(requireContext())
        var dialogBinding = PetRescueBottomSheetBinding.inflate(layoutInflater)
        with(dialog) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(dialogBinding.root)

            show()
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            window?.attributes?.windowAnimations = R.style.DialogAnimation
            window?.setGravity(Gravity.BOTTOM)
        }

        dialogBinding.apply {
            petRescueBottomSheetSave.setOnClickListener {
                with(viewModel) {
                    name.value = petRescueBottomSheetName.text.toString()
                    address.value = petRescueBottomSheetAddress.text.toString()
                }
                dialog.dismiss()
            }
        }

    }

}