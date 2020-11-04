package com.example.billingapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_individual_row.view.*

class Adapter(private val data:List<results>):RecyclerView.Adapter<Adapter.ItemViewAdapter>() {
    class ItemViewAdapter(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        fun bind(serverData:results) {
            itemView.tv_id.text= serverData.id.toString()
            itemView.tv_barcode.text=serverData.barcode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_individual_row, parent, false)
        return ItemViewAdapter(view)
    }

    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int = data.count()
}
