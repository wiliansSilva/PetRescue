package com.example.petrescue.viewModels



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petrescue.api.PetRescueRepository
import com.example.petrescue.model.BreedsImagesModel
import com.example.petrescue.model.BreedsModel
import kotlinx.coroutines.launch
import okio.IOException

class PetRescueViewModel(
    private val repository: PetRescueRepository
): ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData("Informe seu nome")
    val address: MutableLiveData<String> = MutableLiveData("")

    private val _breeds = MutableLiveData<BreedsModel>()
    val breeds: LiveData<BreedsModel> = _breeds

    private val _specificBreed = MutableLiveData<BreedsImagesModel>()
    val sprecificBreed: LiveData<BreedsImagesModel> = _specificBreed

    fun onGetBreeds(){
        viewModelScope.launch {
            try {
                val response = repository.getBreeds()
                _breeds.value = response
            }catch (e: IOException){
                Log.e("PetRescueViewModel",e.message.toString())
            }
        }
    }

    fun onGetBreedsImage(breed: String){
        viewModelScope.launch {
            try {
                val response = repository.getSpecificBreed(breed)
                _specificBreed.value = response
            }catch (e: IOException){
                Log.e("PetRescueViewModel",e.message.toString())
            }
        }
    }

}