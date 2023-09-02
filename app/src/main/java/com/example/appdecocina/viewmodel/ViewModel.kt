package com.example.appdecocina.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appdecocina.api.MainRepository
import com.example.appdecocina.model.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private var repository: MainRepository = MainRepository()

    fun getRecetas(): MutableLiveData<List<Recetas>> {
        val recetas = MutableLiveData<List<Recetas>>()
        GlobalScope.launch(Main) {
            recetas.value = repository.getRecetas()
        }
        return recetas
    }
  /*  fun addRecetas(recetas: Recetas): MutableLiveData<Recetas>?  {
        val recetaresponse = MutableLiveData<Recetas>()
        GlobalScope.launch(Main) {
            recetaresponse.value = repository.addRecetas(recetas)
        }
        return recetaresponse
    }*/
}