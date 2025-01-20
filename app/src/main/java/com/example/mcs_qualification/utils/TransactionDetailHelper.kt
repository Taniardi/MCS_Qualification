package com.example.mcs_qualification.utils

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.mcs_qualification.model.TransactionDetail

class TransactionDetailHelper(private val context: Context) {
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

    fun getTransactionDetial(header_id: Int): ArrayList<TransactionDetail> {
        val transactionDetailList: ArrayList<TransactionDetail> = ArrayList<TransactionDetail>()

        val query =
            "Select * from TransactionDetail where transaction_id = $header_id"
        val cursor = db!!.rawQuery(query, null)
        cursor.moveToFirst()

        var transactionDetail: TransactionDetail

        var id: Int
        var transaction_id: Int
        var item_id: Int

        if (cursor.count > 0) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                transaction_id = cursor.getInt(cursor.getColumnIndexOrThrow("transaction_id"))
                item_id = cursor.getInt(cursor.getColumnIndexOrThrow("item_id"))

                transactionDetail = TransactionDetail(id, transaction_id, item_id)
                transactionDetailList.add(transactionDetail)

                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }

        cursor.close()
        return transactionDetailList
    }

    fun addTransactionDetail(transaction_id: Int, item_id: Int) {
        val query =
            "Insert into TransactionDetail (transaction_id, item_id) values ($transaction_id, $item_id)"
        db!!.execSQL(query)
    }
}
