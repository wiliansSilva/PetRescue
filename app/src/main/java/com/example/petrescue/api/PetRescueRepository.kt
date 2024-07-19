package com.example.petrescue.api

import com.example.petrescue.model.BreedsImagesModel
import com.example.petrescue.model.BreedsModel

interface PetRescueRepository {

    suspend fun getBreeds(): BreedsModel

    suspend fun getSpecificBreed(breed: String): BreedsImagesModel
}