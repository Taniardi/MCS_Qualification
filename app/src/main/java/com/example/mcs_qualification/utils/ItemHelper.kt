package com.example.mcs_qualification.utils

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.mcs_qualification.model.Item

class ItemHelper(private val context: Context) {
    private var database_helper: DatabaseHelper? = null

    private var db: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open() {
        database_helper = DatabaseHelper(context)
        db = database_helper!!.writableDatabase
    }

    @Throws(SQLException::class)
    fun close() {
        database_helper!!.close()
    }

    val item: ArrayList<Item>
        get() {
            val itemList: ArrayList<Item> = ArrayList<Item>()

            val query = "Select * from item"
            val cursor = db!!.rawQuery(query, null)
            cursor.moveToFirst()

            var item: Item
            var id: Int
            var name: String?

            if (cursor.count > 0) {
                do {
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name"))

                    item = Item(id, name)
                    itemList.add(item)

                    cursor.moveToNext()
                } while (!cursor.isAfterLast)
            }

            cursor.close()

            return itemList
        }

    fun addItem(name: String) {
        val query = "Insert into item (name) values ($name)"
        db!!.execSQL(query)
    }
}