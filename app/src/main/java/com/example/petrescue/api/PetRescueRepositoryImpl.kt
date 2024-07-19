package com.example.petrescue.api

import com.example.petrescue.model.BreedsImagesModel
import com.example.petrescue.model.BreedsModel
import com.example.petrescue.network.ApiFactory


class PetRescueRepositoryImpl(private val service: PetRescueService,private val netWork: ApiFactory): PetRescueRepository {


    override suspend fun getBreeds(): BreedsModel {
        return service.getBreeds()
    }

    override suspend fun getSpecificBreed(breed: String): BreedsImagesModel {
        return service.getSpecificBreed(breed)
    }

}