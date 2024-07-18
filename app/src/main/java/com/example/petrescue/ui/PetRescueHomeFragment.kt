package com.example.petrescue.ui

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.petrescue.R
import com.example.petrescue.adapters.PetRescueHomeAdapter
import com.example.petrescue.databinding.FragmentPetRescueHomeBinding
import com.example.petrescue.databinding.PetRescueBottomSheetBinding
import com.example.petrescue.viewModels.PetRescueViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PetRescueHomeFragment : Fragment() {

    private var _binding: FragmentPetRescueHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PetRescueViewModel by viewModel()
    private val adapter by lazy { PetRescueHomeAdapter() }
    private var breed: String = "akita"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetRescueHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGetBreeds()
        viewModel.onGetBreedsImage(breed)

        setupView()
        setupObservables()
    }

    private fun setupView(){
        with(binding){
            petRescueHomeAddress.setOnClickListener {
                showDialog()
            }
            petRescueAnimalRecycler.adapter = adapter
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

    private fun setupObservables(){
        with(viewModel){
            name.observe(viewLifecycleOwner) {
                binding.petRescueHomeAddress.text = it
            }
            sprecificBreed.observe(viewLifecycleOwner){
                if(it != null)
                    adapter.submitData(it.message,breed)
            }
        }
    }

    private fun showDialog(){
        var dialog = Dialog(requireContext())
        var dialogBinding = PetRescueBottomSheetBinding.inflate(layoutInflater)
        with(dialog){
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(dialogBinding.root)

            show()
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            window?.attributes?.windowAnimations = R.style.DialogAnimation
            window?.setGravity(Gravity.BOTTOM)
        }

        dialogBinding.apply {
            petRescueBottomSheetSave.setOnClickListener {
                with(viewModel){
                    name.value = petRescueBottomSheetName.text.toString()
                    address.value = petRescueBottomSheetAddress.text.toString()
                }
                dialog.dismiss()
            }
        }

    }

}