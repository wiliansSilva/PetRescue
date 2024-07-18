package com.example.petrescue

import android.app.Application
import com.example.petrescue.di.PetRescueDIModule
import com.example.petrescue.network.ApiFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class PetRescueApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PetRescueApplication)
            modules(
                listOf(
                    getModule(),
                    getFactory()
                )
            )
        }
    }

    private fun getModule() = PetRescueDIModule.get(
        baseUrl = "https://yourwebsite.net/api/",
        useMock = false
    )

    private fun getFactory(): Module{
        return module {
            single { ApiFactory() }
        }
    }
}