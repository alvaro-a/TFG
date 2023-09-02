package com.example.appdecocina.api

import com.example.appdecocina.model.*
import retrofit2.Response
import retrofit2.http.*
import kotlinx.coroutines.Deferred

interface RecetasService {

    @GET("getrecetas")
    fun getRecetas(): Deferred<Response<Reply>>

    @POST("addrecetas/")
    fun addRecetas(@Body recetas: Recetas): Deferred<Response<Reply>>


}