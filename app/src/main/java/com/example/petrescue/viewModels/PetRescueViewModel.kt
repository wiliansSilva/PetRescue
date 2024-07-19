package com.example.petrescue.viewModels



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petrescue.api.PetRescueRepository
import com.example.petrescue.model.BreedsImagesModel
import com.example.petrescue.model.BreedsModel
import com.example.petrescue.utils.Resource
import com.example.petrescue.utils.launchFromNetwork
import kotlinx.coroutines.launch
import okio.IOException

class PetRescueViewModel(
    private val repository: PetRescueRepository
): ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData("Informe seu nome")
    val address: MutableLiveData<String> = MutableLiveData("")
    val images: MutableList<String> = mutableListOf()
    var breed: String = "akita"

    private val _breeds = MutableLiveData<BreedsModel>()
    var breeds: LiveData<BreedsModel> = _breeds

    private val _specificBreed = MutableLiveData<Resource<BreedsImagesModel>>()
    val sprecificBreed: LiveData<Resource<BreedsImagesModel>> = _specificBreed

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
        launchFromNetwork(_specificBreed){
            repository.getSpecificBreed(breed)
        }
    }

}