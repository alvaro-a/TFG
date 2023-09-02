package com.example.appdecocina.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdecocina.R
import com.example.appdecocina.model.Recetas



class MainAdapter (val context: Context,
                   val layout: Int
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var dataList: List<Recetas> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    internal fun setRecetas(recetas:List<Recetas>) {
        this.dataList = recetas
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Recetas){
            val tvrecetas = itemView.findViewById(R.id.tvreceta) as TextView
            val imgrecetas = itemView.findViewById(R.id.imgrecetas) as ImageView

            tvrecetas.text = dataItem.Nombre



            try {
                val imageBytes = Base64.decode(dataItem.Imagen, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                imgrecetas.setImageBitmap(decodedImage)
            } catch (e: Exception) {
                imgrecetas.setImageResource(R.drawable.error)            }




            itemView.tag = dataItem
        }
    }
}