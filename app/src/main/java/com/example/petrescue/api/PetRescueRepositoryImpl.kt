package com.example.petrescue.api

import android.net.Network

class PetRescueRepositoryImpl(
    private val network: Network,
    private val service: PetRescueService
) : PetRescueRepository {



}