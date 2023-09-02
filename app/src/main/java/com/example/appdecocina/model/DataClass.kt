package com.example.appdecocina.model

import java.io.Serializable

data class Reply(var recetas:List<Recetas>):Serializable
data class Recetas(var Nombre: String="",var Descripcion: String="",var Categoria: String="",var Imagen: String=""): Serializable