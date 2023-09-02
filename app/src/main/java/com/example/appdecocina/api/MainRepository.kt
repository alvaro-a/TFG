package com.example.appdecocina.api

import android.util.Log
import com.example.appdecocina.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository {
    val service = WebAccess.recetasService

    suspend fun getRecetas(): List<Recetas> {
        val MAX_RETRIES = 100
        val RETRY_DELAY_MS = 5000L

        var retries = 0

        while (retries < MAX_RETRIES) {
            try {
                Log.d("MiApp", "Llamando a la API")
                val webResponse = service.getRecetas().await()
                if (webResponse.isSuccessful) {
                    Log.d("MiApp", "Todo bien")
                    return webResponse.body()?.recetas ?: emptyList()
                } else {
                    Log.e("MiApp", "Error")
                }
            } catch (e: Exception) {
            }
            retries++
            if (retries < MAX_RETRIES) {
                delay(RETRY_DELAY_MS)
            }
        }

        return emptyList()
    }


    /*suspend fun addRecetas(receta: Recetas): Recetas? {
        var recetaresponse: Recetas? = null
        val webResponse = service.addRecetas(receta).await()
        if (webResponse.isSuccessful) {
            recetaresponse = webResponse.body()!!.receta
        }
        return recetaresponse
    } */
}