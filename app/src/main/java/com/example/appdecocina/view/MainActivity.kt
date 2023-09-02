package com.example.appdecocina.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdecocina.R
import com.example.appdecocina.adapter.MainAdapter
import com.example.appdecocina.model.Recetas
import com.example.appdecocina.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var rvrecetas: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var recetas: List<Recetas>
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findView()
        initRV()
       viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getRecetas().observe(this, Observer { it ->
            it?.let{
                recetas=it
                adapter.setRecetas(recetas)
            }
        })
    }
    private fun findView() {
        rvrecetas = findViewById(R.id.rvrecetas)
    }
     private fun initRV() {
         adapter = MainAdapter(this, R.layout.rowrecetas)
         rvrecetas.layoutManager = LinearLayoutManager(this)
         rvrecetas.adapter = adapter
     }
    fun onClickReceta(v: View){
        val receta = v.tag as Recetas
        val base64String = receta.Imagen
        val byteArray = Base64.decode(base64String, Base64.DEFAULT)
        receta.Imagen=""
        val intent = Intent(this, RecetaActivity::class.java)
        intent.putExtra("receta" , receta)
        /*intent.putExtra("byteArray", byteArray) */
        startActivity(intent)
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