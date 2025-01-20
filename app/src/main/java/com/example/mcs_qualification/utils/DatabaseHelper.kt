package com.example.mcs_qualification.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, "TitiStore", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val queryCreateTableUser = "Create table User(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "gender INT NOT NULL)"

        val queryCreateTableItem = "Create table Item(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL)"

        val queryCreateTableTransactionHeader = "Create table TransactionHeader(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "transaction_date DATE NOT NULL)"

        val queryCreateTableTransactionDetail = "Create table TransactionDetail(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "transaction_id INTEGER NOT NULL," +
                "item_id INTEGER NOT NULL)"


        db.execSQL(queryCreateTableUser)
        db.execSQL(queryCreateTableItem)
        db.execSQL(queryCreateTableTransactionHeader)
        db.execSQL(queryCreateTableTransactionDetail)

        val queryInsertItem = "Insert into item (name) values " +
                "('Buggy Snacks'), " +
                "('404 Coffee'), " +
                "('NullPointer'), " +
                "('Syntax Sugar Rush'), " +
                "('Infinite Loop Tea'), " +
                "('Debug Donuts'), " +
                "('VersionSnack 1.0'), " +
                "('Pixel Pasta'), " +
                "('Code & Chill Blanket') , " +
                "('Commit Cola')"
        db.execSQL(queryInsertItem)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists TransactionDetail")
        db.execSQL("drop table if exists TransactionHeader")
        db.execSQL("drop table if exists User")
        db.execSQL("drop table if exists Item")

        onCreate(db)
    }
}
