package com.example.mcs_qualification.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mcs_qualification.R
import com.example.mcs_qualification.activity.MainActivity
import com.example.mcs_qualification.model.Item
import com.example.mcs_qualification.model.TransactionDetail
import java.util.function.Predicate


class ItemHistoryAdapter(
    listItem: ArrayList<TransactionDetail>,
    ctx: MainActivity,
    itemList: ArrayList<Item>
) :
    RecyclerView.Adapter<ItemHistoryAdapter.VH>() {
    private val listItem: ArrayList<TransactionDetail> = listItem
    private val ctx: MainActivity = ctx
    private val itemList: ArrayList<Item> = itemList

    inner class VH(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(item: TransactionDetail) {
            val lblItemHistory = itemView.findViewById<TextView>(R.id.lblItemHisotry)
            lblItemHistory.setText(
                itemList.stream()
                    .filter(Predicate<Item> { x: Item -> x.id === item.item_id })
                    .findFirst().get().name
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_item_history, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}
