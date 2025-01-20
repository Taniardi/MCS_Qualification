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
import com.example.mcs_qualification.model.TransactionHeader
import com.example.mcs_qualification.utils.ItemHelper
import com.example.mcs_qualification.utils.TransactionDetailHelper
import java.text.SimpleDateFormat


class HistoryAdapter(listItem: ArrayList<TransactionHeader>, ctx: MainActivity) :
    RecyclerView.Adapter<HistoryAdapter.VH>() {
    private val listItem: ArrayList<TransactionHeader> = listItem
    private val ctx: MainActivity = ctx

    inner class VH(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(item: TransactionHeader) {
            val lblDate = itemView.findViewById<TextView>(R.id.lblDate)
            val formatter = SimpleDateFormat("EEEE dd MMMM yyyy")
            lblDate.text = formatter.format(item.date)

            val itemRecycler = itemView.findViewById<RecyclerView>(R.id.recycleItemHistory)

            val helper: TransactionDetailHelper = TransactionDetailHelper(itemView.context)
            helper.open()
            val tdList: ArrayList<TransactionDetail> = helper.getTransactionDetial(item.id)
            helper.close()

            val itemHelper: ItemHelper = ItemHelper(itemView.context)
            itemHelper.open()
            val itemList: ArrayList<Item> = itemHelper.item
            itemHelper.close()

            itemRecycler.setAdapter(ItemHistoryAdapter(tdList, ctx, itemList))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}
