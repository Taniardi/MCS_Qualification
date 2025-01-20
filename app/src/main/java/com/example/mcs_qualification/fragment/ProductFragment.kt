package com.example.mcs_qualification.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mcs_qualification.R
import com.example.mcs_qualification.adapter.ProductAdapter
import com.example.mcs_qualification.model.Item
import com.example.mcs_qualification.utils.ItemHelper


class ProductFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        val helper: ItemHelper = ItemHelper(view.context)
        helper.open()
        val itemList: ArrayList<Item> = helper.item
        helper.close()

        val recycleProduct = view.findViewById<RecyclerView>(R.id.recycleProduct)
        recycleProduct.adapter = ProductAdapter(itemList, view.context)
        return view
    }
}