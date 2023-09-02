package com.example.appdecocina.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appdecocina.R
import com.example.appdecocina.model.Recetas
import com.example.appdecocina.viewmodel.ViewModel
import com.squareup.picasso.Picasso

class RecetaActivity: AppCompatActivity() {
    private lateinit var receta: Recetas
    private lateinit var tvrecetaRe: TextView
    private lateinit var tvdescRe: TextView
    private lateinit var imgrecetaRe: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receta)
        receta = intent.getSerializableExtra("receta") as Recetas
        /*val receivedByteArray = intent.getByteArrayExtra("byteArray") */
        tvdescRe = findViewById(R.id.tvDescRe)
        tvrecetaRe = findViewById(R.id.tvNombreRe)
        imgrecetaRe = findViewById(R.id.ivRe)

        tvrecetaRe.text = receta.Nombre
        tvdescRe.text = receta.Descripcion

        /* if (receivedByteArray != null) {
            val bitmap = BitmapFactory.decodeByteArray(receivedByteArray, 0, receivedByteArray.size)
            imgrecetaRe.setImageBitmap(bitmap)
        }*/
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    fun onClickInicio(item: MenuItem) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickSubir(item: MenuItem) {
        val intent = Intent(this, SubirActivity::class.java)
        startActivity(intent)
    }
}