package com.example.petrescue.ui

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.example.petrescue.R
import com.example.petrescue.databinding.FragmentPetRescueHomeBinding
import com.example.petrescue.databinding.FragmentPetRescueSplashBinding
import com.example.petrescue.databinding.PetRescueBottomSheetBinding
import com.example.petrescue.viewModels.PetRescueViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PetRescueHomeFragment : Fragment() {

    private var _binding: FragmentPetRescueHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PetRescueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetRescueHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservables()
    }

    private fun setupView(){
        with(binding){
            petRescueHomeAddress.setOnClickListener {
                showDialog()
            }
        }
    }

    private fun setupObservables(){
        with(viewModel){
            name.observe(viewLifecycleOwner) {
                binding.petRescueHomeAddress.text = it
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