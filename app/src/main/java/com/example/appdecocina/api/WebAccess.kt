package com.example.appdecocina.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebAccess {

    val recetasService: RecetasService by lazy {
        val retrofit = Retrofit.Builder()

            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://192.168.0.14:5000/")
            .build()

        retrofit.create(RecetasService::class.java)
    }
}