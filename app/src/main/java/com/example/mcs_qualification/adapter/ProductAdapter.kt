package com.example.mcs_qualification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mcs_qualification.R
import com.example.mcs_qualification.model.Item
import com.example.mcs_qualification.repository.CartRepository


class ProductAdapter(listItem: ArrayList<Item>, private val ctx: Context) :
    RecyclerView.Adapter<ProductAdapter.VH>() {
    private val listItem: ArrayList<Item> = listItem

    inner class VH(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(item: Item) {
            val lblProductName = itemView.findViewById<TextView>(R.id.lblProductName)
            val btnBuy = itemView.findViewById<Button>(R.id.btnBuy)

            lblProductName.setText(item.name)
            btnBuy.setOnClickListener { x: View? ->
                if (!CartRepository.instance!!.getItemList().stream().anyMatch { z ->
                        z.equals(item)
                    }) {
                    CartRepository.instance!!.addItem(item)
                    Toast.makeText(ctx, "Successfully add item", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, "Item already on cart", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}
