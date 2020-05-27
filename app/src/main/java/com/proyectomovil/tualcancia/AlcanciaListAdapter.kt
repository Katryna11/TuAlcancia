package com.proyectomovil.tualcancia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlcanciaListAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<AlcanciaListAdapter.AlcanciaViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var total = emptyList<Int>()

    inner class AlcanciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val totalItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcanciaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent,false)
        return AlcanciaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlcanciaViewHolder, position: Int) {
        val current = total[position]

        holder.totalItemView.text = current.toString()
    }

    internal fun setValor(alcancia: List<Int>){
        this.total = alcancia
        notifyDataSetChanged()
    }

    override fun getItemCount() = total.count()
}