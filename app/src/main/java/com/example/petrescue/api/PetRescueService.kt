package com.example.petrescue.api

import com.example.petrescue.model.BreedsImagesModel
import com.example.petrescue.model.BreedsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PetRescueService {

    @GET("breeds/list/all")
    suspend fun getBreeds(): BreedsModel

    @GET("breed/{breed}/images")
    suspend fun getSpecificBreed(
        @Path("breed") breed: String
    ): BreedsImagesModel

}