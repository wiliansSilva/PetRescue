package com.example.petrescue.mockService

import android.content.Context
import com.example.petrescue.api.PetRescueService
import com.example.petrescue.model.BreedsImagesModel
import com.example.petrescue.model.BreedsModel

class PetRescueMockService(context: Context): PetRescueService {
    override suspend fun getBreeds(): BreedsModel {
        TODO("Not yet implemented")
    }

    override suspend fun getSpecificBreed(breed: String): BreedsImagesModel {
        TODO("Not yet implemented")
    }

}