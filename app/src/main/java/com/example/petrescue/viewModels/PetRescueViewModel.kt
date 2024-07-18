package com.example.petrescue.viewModels



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petrescue.api.PetRescueRepository

class PetRescueViewModel(
    private val repository: PetRescueRepository
): ViewModel() {


    val name: MutableLiveData<String> = MutableLiveData("Informe seu nome")
    val address: MutableLiveData<String> = MutableLiveData("")


}