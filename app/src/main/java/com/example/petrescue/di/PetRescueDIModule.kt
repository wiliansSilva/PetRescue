package com.example.petrescue.di

import com.example.petrescue.api.PetRescueRepository
import com.example.petrescue.api.PetRescueRepositoryImpl
import com.example.petrescue.api.PetRescueService
import com.example.petrescue.mockService.PetRescueMockService
import com.example.petrescue.network.ApiFactory
import com.example.petrescue.viewModels.PetRescueViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


object PetRescueDIModule {

    fun get(
        baseUrl: String,
        useMock: Boolean,
    ): Module {
        return module {

            single {
                if(useMock){
                    PetRescueMockService(androidApplication())
                }else{
                    get<ApiFactory>().create(
                        PetRescueService::class.java,
                        baseUrl,
                    )
                }
            }

            single<PetRescueRepository>{
                PetRescueRepositoryImpl(get(),get())
            }

            viewModel {
                PetRescueViewModel(get())
            }
        }
    }

}