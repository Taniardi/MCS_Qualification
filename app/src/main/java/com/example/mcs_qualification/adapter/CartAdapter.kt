package com.example.mcs_qualification.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mcs_qualification.R
import com.example.mcs_qualification.activity.MainActivity
import com.example.mcs_qualification.fragment.CartFragment
import com.example.mcs_qualification.model.Item
import com.example.mcs_qualification.repository.CartRepository


class CartAdapter(listItem: ArrayList<Item>, ctx: MainActivity) :
    RecyclerView.Adapter<CartAdapter.VH>() {
    private val listItem: ArrayList<Item> = listItem
    private val ctx: MainActivity = ctx

    inner class VH(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(item: Item) {
            with(itemView){
                val lblProductName = itemView.findViewById<TextView>(R.id.lblProductName)
                val btnBuy = itemView.findViewById<Button>(R.id.btnBuy)

                lblProductName.setText(item.name)
                btnBuy.text = "Delete"
                btnBuy.setOnClickListener { x: View? ->
                    CartRepository.instance!!.removeItem(item)
                    Toast.makeText(ctx, "Item successfully deleted!", Toast.LENGTH_SHORT).show()
                    ctx.setFragment(CartFragment())
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