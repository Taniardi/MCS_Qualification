package com.example.mcs_qualification.repository

import com.example.mcs_qualification.model.Item


class CartRepository private constructor() {
    private val itemList: ArrayList<Item>

    init {
        this.itemList = ArrayList<Item>()
    }

    fun getItemList(): ArrayList<Item> {
        return itemList
    }

    fun addItem(item: Item) {
        itemList.add(item)
    }

    fun removeItem(item: Item) {
        itemList.remove(item)
    }

    fun clearItem() {
        itemList.clear()
    }

    companion object {
        var instance: CartRepository? = null
            get() {
                if (field == null) field = CartRepository()
                return field
            }
            private set
    }
}
