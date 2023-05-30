package com.example.dresstique_android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val clothesList : ArrayList<Clothes>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.clothes_item, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = clothesList[position]

        holder.name.text = currentitem.name
        holder.description.text = currentitem.description
        holder.price.text = currentitem.price

        val btnShare = holder.itemView.findViewById<ImageButton>(R.id.btnShare)

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Look what I found on Dresstique\n Name:\n${currentitem.name}\n")
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }


    override fun getItemCount(): Int {
        return clothesList.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.itemName)
        val description : TextView = itemView.findViewById(R.id.itemDescription)
        val price : TextView = itemView.findViewById(R.id.itemPrice)
    }
}