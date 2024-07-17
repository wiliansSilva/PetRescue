package com.example.petrescue.network

import com.google.gson.Gson
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiFactory{

    fun <T> create(
        service: Class<T>,baseUrl: String,interceptors: List<Interceptor> = emptyList()
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(service)
    }
}